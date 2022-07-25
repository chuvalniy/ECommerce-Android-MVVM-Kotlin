package com.example.feature_cart.presentation.epoxy.models

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_cart.R
import com.example.feature_cart.databinding.ShimmerCartItemBinding

class ShimmerCartItemEpoxyModel :
    ViewBindingKotlinModel<ShimmerCartItemBinding>(R.layout.shimmer_cart_item) {

    override fun ShimmerCartItemBinding.bind() {
        shimmerLayout.startShimmer()
    }

    override fun ShimmerCartItemBinding.unbind() {
        shimmerLayout.stopShimmer()
    }
}