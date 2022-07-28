package com.example.feature_main_screen.presentation.home_screen.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import androidx.core.graphics.withTranslation
import com.example.feature_main_screen.R

class CategoryView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    private val rowPaint = Paint().apply { style = Paint.Style.FILL }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.category_view_title_text_size)
    }

    private val circleColors = listOf(
        ContextCompat.getColor(context, R.color.light_orange),
        ContextCompat.getColor(context, R.color.white)
    )

    private val textColors = listOf(
        ContextCompat.getColor(context, R.color.light_orange),
        ContextCompat.getColor(context, R.color.dark_blue)
    )

    private val iconColors = listOf(
        ContextCompat.getColor(context, R.color.white),
        ContextCompat.getColor(context, R.color.gray)
    )

    private val circleSize = resources.getDimensionPixelSize(R.dimen.category_view_circle_size)

    private val textHeight = resources.getDimensionPixelSize(R.dimen.category_view_text_height)

    private lateinit var bitmap: Bitmap

    private val contentHeight = circleSize + textHeight

    private val lastPoint = PointF()
    private var lastPointerId = 0

    private val transformations = Transformations()

    private val iconRect = RectF(0F, 0F, 0F, 0F)
    private val rowRect = Rect(0, 0, 0, 0)

    private val iconHorizontalPadding =
        resources.getDimensionPixelSize(R.dimen.category_view_item_horizontal_padding)

    private val contentWidth: Int
        get() = (circleSize + iconHorizontalPadding) * tabs.size

    private var tabs = emptyList<CategoryItem>()

    fun setTabs(tabs: List<CategoryItem>) {
        if (tabs != this.tabs) {
            this.tabs = tabs

            requestLayout()

            invalidate()
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).also {
            it.currentSelectedPosition = currentlySelectedTabId
            it.translationX = transformations.translationX
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            currentlySelectedTabId = state.currentSelectedPosition
            transformations.translationX = state.translationX
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private var currentlySelectedTabId: Int = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            contentWidth
        } else {
            MeasureSpec.getSize(widthMeasureSpec)
        }

        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.UNSPECIFIED -> contentHeight
            MeasureSpec.EXACTLY -> heightMeasureSpec
            MeasureSpec.AT_MOST -> contentHeight.coerceAtMost(heightSpecSize)
            else -> error("Unreachable")
        }

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rowRect.set(0, 0, w, contentHeight)
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        withTranslation(x = transformations.translationX) {
            bitmap = createBitmap(contentWidth, contentHeight).applyCanvas {
                drawTabs()
            }
            drawBitmap(bitmap, 0F, 0F, rowPaint)
        }
    }

    private fun Canvas.drawTabs() {
        tabs.onEachIndexed { index, tab ->

            val iconRect = getTabRect(index)

            val icon: Drawable? = ContextCompat.getDrawable(context, tab.icon)

            if (currentlySelectedTabId == index) {
                circlePaint.color = circleColors[0]
                textPaint.color = textColors[0]
                icon?.setTint(iconColors[0])
            } else {
                circlePaint.color = circleColors[1]
                textPaint.color = textColors[1]
                icon?.setTint(iconColors[1])
            }

            drawCircle(
                iconRect.centerX(),
                iconRect.centerY(),
                (circleSize / 2).toFloat(),
                circlePaint
            )

            val titleY =
                textPaint.getTextBaselineByCenter((contentHeight + circleSize).toFloat() / 2)
            val titleX =
                (iconHorizontalPadding + circleSize) * (index + 0.5f) - textPaint.measureText(tab.title) / 2

            drawText(tab.title, titleX, titleY, textPaint)

            icon?.let {
                val startX = (iconRect.centerX() - icon.intrinsicWidth / 2).toInt()
                val endX = (iconRect.centerX() + icon.intrinsicWidth / 2).toInt()
                val startY = circleSize / 2 - icon.intrinsicHeight / 2
                val endY = circleSize / 2 + icon.intrinsicHeight / 2

                icon.setBounds(startX, startY, endX, endY)
                icon.draw(this)
            }
        }
    }

    private fun getTabRect(index: Int): RectF {
        iconRect.left = (index * (circleSize + iconHorizontalPadding)).toFloat()
        iconRect.top = 0F
        iconRect.right = iconRect.left + circleSize + iconHorizontalPadding
        iconRect.bottom = iconRect.top + circleSize

        return iconRect
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false

        return if (event.pointerCount == 1) processMove(event) else false
    }

    private fun processMove(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastPoint.set(event.x, event.y)
                lastPointerId = event.getPointerId(0)

                currentlySelectedTabId = getTab(event)
                invalidate()
                true
            }
            MotionEvent.ACTION_MOVE -> {
                if (width < contentWidth) {
                    val pointerId = event.getPointerId(0)

                    if (lastPointerId == pointerId) {
                        transformations.addTranslation(lastPoint.x - event.x)
                    }

                    lastPoint.set(event.x, event.y)
                    lastPointerId = event.getPointerId(0)

                    true
                } else false
            }
            else -> false
        }
    }

    private fun getTab(event: MotionEvent): Int {
        return ((event.x - rowRect.left) / (circleSize + iconHorizontalPadding)).toInt()
    }

    private fun Paint.getTextBaselineByCenter(center: Float) = center - (descent() + ascent()) / 2

    private inner class Transformations {
        var translationX = 0F

        private val minTranslation: Float
            get() = (width - contentWidth).toFloat().coerceAtMost(0F)

        fun addTranslation(dx: Float) {
            translationX = (translationX - dx).coerceIn(minTranslation, 0F)
            invalidate()
        }
    }

    private class SavedState : BaseSavedState {
        var translationX: Float = 0F
        var currentSelectedPosition: Int = 0

        constructor(superState: Parcelable?): super(superState)

        constructor(source: Parcel?): super(source) {
            source?.apply {
                translationX = readFloat()
                currentSelectedPosition = readInt()
            }
        }

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)
            out?.writeFloat(translationX)
            out?.writeInt(currentSelectedPosition)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(p0: Parcel): SavedState {
                    return SavedState(p0)
                }

                override fun newArray(p0: Int): Array<SavedState?> {
                    return Array(p0) { null }
                }
            }
        }
    }
}