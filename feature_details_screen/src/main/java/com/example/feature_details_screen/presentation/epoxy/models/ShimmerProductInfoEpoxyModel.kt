package com.example.feature_details_screen.presentation.epoxy.models

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.ShimmerProductInfoBinding

class ShimmerProductInfoEpoxyModel :
    ViewBindingKotlinModel<ShimmerProductInfoBinding>(R.layout.shimmer_product_info) {

    override fun ShimmerProductInfoBinding.bind() {
        productInfoShimmerLayout.startShimmer()
    }

    override fun ShimmerProductInfoBinding.unbind() {
        productInfoShimmerLayout.stopShimmer()
    }
}
