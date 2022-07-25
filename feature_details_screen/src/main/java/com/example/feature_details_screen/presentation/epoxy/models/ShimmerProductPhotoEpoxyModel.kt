package com.example.feature_details_screen.presentation.epoxy.models

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.ShimmerProductPhotoBinding

class ShimmerProductPhotoEpoxyModel :
    ViewBindingKotlinModel<ShimmerProductPhotoBinding>(R.layout.shimmer_product_photo) {

    override fun ShimmerProductPhotoBinding.bind() {
        productPhotoShimmerLayout.startShimmer()
    }

    override fun ShimmerProductPhotoBinding.unbind() {
        productPhotoShimmerLayout.stopShimmer()
    }
}