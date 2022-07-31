package com.example.feature_details_screen.presentation.epoxy.models.product_title

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R

@EpoxyModelClass
abstract class DetailsProductTitleModel :
    EpoxyModelWithHolder<DetailsProductTitleModel.ProductInfoHeaderHolder>() {

    @EpoxyAttribute
    lateinit var productTitle: String

    override fun getDefaultLayout(): Int {
        return R.layout.details_title
    }

    override fun bind(holder: ProductInfoHeaderHolder) {
        holder.title.text = productTitle
    }

    class ProductInfoHeaderHolder : KotlinEpoxyHolder() {
        val title by bind<TextView>(R.id.tvTitle)
    }
}