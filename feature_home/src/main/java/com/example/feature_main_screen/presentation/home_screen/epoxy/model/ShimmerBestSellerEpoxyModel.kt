package com.example.feature_main_screen.presentation.home_screen.epoxy.model

import android.view.ViewGroup
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ShimmerBestSellerItemBinding

data class ShimmerBestSellerEpoxyModel(
    val index: Int
) : ViewBindingKotlinModel<ShimmerBestSellerItemBinding>(R.layout.shimmer_best_seller_item) {

    override fun ShimmerBestSellerItemBinding.bind() {
        setupMargins()

        bestSellerShimmerLayout.startShimmer()
    }

    override fun ShimmerBestSellerItemBinding.unbind() {
        bestSellerShimmerLayout.stopShimmer()
    }

    private fun ShimmerBestSellerItemBinding.setupMargins() {
        val params: ViewGroup.MarginLayoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
        when (index % 2) {
            0 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.home_padding),
                0,
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_horizontal),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_bottom)
            )
            1 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_horizontal),
                0,
                root.context.resources.getDimensionPixelSize(R.dimen.home_padding),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_bottom)
            )
        }
        root.layoutParams = params
    }
}