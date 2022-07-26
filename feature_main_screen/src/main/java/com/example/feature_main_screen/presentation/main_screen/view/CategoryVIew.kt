package com.example.feature_main_screen.presentation.main_screen.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
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

    private val selectedTabCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.light_orange)
    }
    private val unselectedTabCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val selectedTabTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.category_view_title_text_size)
        color = ContextCompat.getColor(context, R.color.light_orange)
    }
    private val unselectedTabTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.category_view_title_text_size)
        color = ContextCompat.getColor(context, R.color.dark_blue)
    }

    private val rowPaint = Paint().apply { style = Paint.Style.FILL }

    private val selectedTabIconColor = ContextCompat.getColor(context, R.color.white)
    private val unselectedTabIconColor = ContextCompat.getColor(context, R.color.gray)

    private val circleSize = resources.getDimensionPixelSize(R.dimen.category_view_circle_size)

    private val textHeight = resources.getDimensionPixelSize(R.dimen.category_view_text_height)

    private lateinit var bitmap: Bitmap


    private val contentHeight = circleSize + textHeight

    private val lastPoint = PointF()
    private var lastPointerId = 0

    private val transformations = Transformations()

    private val iconRect = RectF(0F, 0F, 0F, 0F)
    private val rowRect = Rect(0, 0, 0, 0)

    private val iconHorizontalPadding = 72


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

            if (index == currentlySelectedTabId) {
                drawCircle(
                    iconRect.centerX(),
                    iconRect.centerY(),
                    (circleSize / 2).toFloat(),
                    selectedTabCirclePaint
                )

                val titleY =
                    selectedTabTextPaint.getTextBaselineByCenter((contentHeight + circleSize).toFloat() / 2)
                val titleX =
                    (iconHorizontalPadding + circleSize) * (index + 0.5f) - selectedTabTextPaint.measureText(
                        tab.title
                    ) / 2
                drawText(tab.title, titleX, titleY, selectedTabTextPaint)


                val drawable: Drawable? = ContextCompat.getDrawable(context, tab.icon)

                drawable?.let {

                    val startX = (iconRect.centerX() - drawable.intrinsicWidth / 2).toInt()
                    val endX = (iconRect.centerX() + drawable.intrinsicWidth / 2).toInt()
                    val startY = circleSize / 2 - drawable.intrinsicHeight / 2
                    val endY =
                        circleSize / 2 + drawable.intrinsicHeight - drawable.intrinsicHeight / 2

                    drawable.setBounds(startX, startY, endX, endY)

                    drawable.setTint(selectedTabIconColor)
                    drawable.draw(this)
                }
            } else {

                drawCircle(
                    iconRect.centerX(),
                    iconRect.centerY(),
                    (circleSize / 2).toFloat(),
                    unselectedTabCirclePaint
                )

                val titleY =
                    unselectedTabTextPaint.getTextBaselineByCenter((contentHeight + circleSize).toFloat() / 2)
                val titleX =
                    (iconHorizontalPadding + circleSize) * (index + 0.5f) - unselectedTabTextPaint.measureText(
                        tab.title
                    ) / 2
                drawText(tab.title, titleX, titleY, unselectedTabTextPaint)

                val drawable: Drawable? = ContextCompat.getDrawable(context, tab.icon)

                drawable?.let {

                    val xStart = (iconRect.centerX() - drawable.intrinsicWidth / 2).toInt()
                    val xEnd = (iconRect.centerX() + drawable.intrinsicWidth / 2).toInt()
                    val yStart = circleSize / 2 - drawable.intrinsicHeight / 2
                    val yEnd =
                        circleSize / 2 + drawable.intrinsicHeight - drawable.intrinsicHeight / 2

                    drawable.setBounds(xStart, yStart, xEnd, yEnd)

                    drawable.setTint(unselectedTabIconColor)
                    drawable.draw(this)
                }
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
                        transformations.addTranslation(event.x - lastPoint.x)
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
            private set

        private val minTranslation: Float
            get() = (width - contentWidth).toFloat().coerceAtMost(0F)

        fun addTranslation(dx: Float) {
            translationX = (translationX - dx).coerceIn(minTranslation, 0F)
            invalidate()
        }
    }
}