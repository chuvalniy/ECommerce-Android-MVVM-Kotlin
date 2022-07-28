package com.example.feature_main_screen.presentation.home_screen.epoxy.model

import androidx.core.view.isVisible
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ShimmerHotSalesItemBinding

class ShimmerHotSalesEpoxyModel :
    ViewBindingKotlinModel<ShimmerHotSalesItemBinding>(R.layout.shimmer_hot_sales_item) {

    override fun ShimmerHotSalesItemBinding.bind() {
        hotSalesShimmerLayout.startShimmer()

        hotSalesItem.btnBuyNow.isVisible = false
    }

    override fun ShimmerHotSalesItemBinding.unbind() {
        hotSalesShimmerLayout.stopShimmer()
    }
}