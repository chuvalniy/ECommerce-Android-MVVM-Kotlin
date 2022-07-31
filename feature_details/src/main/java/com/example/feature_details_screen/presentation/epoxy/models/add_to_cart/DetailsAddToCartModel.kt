package com.example.feature_details_screen.presentation.epoxy.models.add_to_cart

import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R

@EpoxyModelClass
abstract class DetailsAddToCartModel :
    EpoxyModelWithHolder<DetailsAddToCartModel.ProductInfoAddToCartHolder>() {

    @EpoxyAttribute
    lateinit var productPrice: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onAddToCartClick: () -> Unit

    override fun bind(holder: ProductInfoAddToCartHolder) {
        holder.textViewProductPrice.text = productPrice
        holder.buttonAddToCart.setOnClickListener { onAddToCartClick() }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_add_to_cart
    }

    class ProductInfoAddToCartHolder : KotlinEpoxyHolder() {
        val textViewProductPrice by bind<TextView>(R.id.tvProductPrice)
        val buttonAddToCart by bind<FrameLayout>(R.id.btnAddToCart)
    }

}