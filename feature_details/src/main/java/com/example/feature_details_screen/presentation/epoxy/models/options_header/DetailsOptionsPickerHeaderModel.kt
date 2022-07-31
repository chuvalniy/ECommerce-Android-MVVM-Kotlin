package com.example.feature_details_screen.presentation.epoxy.models.options_header

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R

@EpoxyModelClass
abstract class DetailsOptionsPickerHeaderModel :
    EpoxyModelWithHolder<DetailsOptionsPickerHeaderModel.ProductInfoColorHeaderHolder>() {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: ProductInfoColorHeaderHolder) {
        holder.optionsTitle.text = title
    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_color_header
    }

    class ProductInfoColorHeaderHolder : KotlinEpoxyHolder() {
        val optionsTitle by bind<TextView>(R.id.tvOptions)

    }
}