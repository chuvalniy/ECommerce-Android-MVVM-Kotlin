package com.example.feature_details_screen.presentation.epoxy.models.add_to_cart

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.facebook.shimmer.ShimmerFrameLayout

@EpoxyModelClass
abstract class ShimmerAddToCartModel : EpoxyModelWithHolder<ShimmerAddToCartModel.ShimmerAddToCartHolder>(){

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_details_add_to_cart
    }

    override fun bind(holder: ShimmerAddToCartHolder) {
        holder.shimmerLayoutAddToCart.startShimmer()
    }

    override fun unbind(holder: ShimmerAddToCartHolder) {
        holder.shimmerLayoutAddToCart.stopShimmer()
    }

    class ShimmerAddToCartHolder : KotlinEpoxyHolder() {
        val shimmerLayoutAddToCart by bind<ShimmerFrameLayout>(R.id.addToCartShimmerLayout)
    }

}