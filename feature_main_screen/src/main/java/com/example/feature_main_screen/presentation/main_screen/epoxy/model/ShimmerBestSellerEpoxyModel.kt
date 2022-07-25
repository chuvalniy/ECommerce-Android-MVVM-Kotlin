package com.example.feature_main_screen.presentation.main_screen.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ShimmerBestSellerItemBinding

class ShimmerBestSellerEpoxyModel :
    ViewBindingKotlinModel<ShimmerBestSellerItemBinding>(R.layout.shimmer_best_seller_item) {

    override fun ShimmerBestSellerItemBinding.bind() {
        bestSellerShimmerLayout.startShimmer()
    }

    override fun ShimmerBestSellerItemBinding.unbind() {
        bestSellerShimmerLayout.stopShimmer()
    }
}