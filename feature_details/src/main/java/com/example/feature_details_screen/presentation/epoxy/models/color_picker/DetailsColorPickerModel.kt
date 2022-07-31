package com.example.feature_details_screen.presentation.epoxy.models.color_picker

import android.graphics.Color
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.google.android.material.card.MaterialCardView

@EpoxyModelClass
abstract class DetailsColorPickerModel :
    EpoxyModelWithHolder<DetailsColorPickerModel.ProductInfoColorHolder>() {

    @EpoxyAttribute
    lateinit var color: String

    override fun bind(holder: ProductInfoColorHolder) {
        holder.colorCardView.setCardBackgroundColor(Color.parseColor(color))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_color_item
    }

    class ProductInfoColorHolder : KotlinEpoxyHolder() {
        val colorCardView by bind<MaterialCardView>(R.id.cvColor)
    }
}