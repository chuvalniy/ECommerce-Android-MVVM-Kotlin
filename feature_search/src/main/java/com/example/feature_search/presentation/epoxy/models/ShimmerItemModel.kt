package com.example.feature_search.presentation.epoxy.models

import android.view.ViewGroup
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.ShimmerSearchItemBinding

class ShimmerItemModel(
    private val index: Int
) : ViewBindingKotlinModel<ShimmerSearchItemBinding>(R.layout.shimmer_search_item) {

    override fun ShimmerSearchItemBinding.bind() {
        setupMargins()

        shimmerLayoutSearchItem.startShimmer()
    }

    override fun ShimmerSearchItemBinding.unbind() {
        shimmerLayoutSearchItem.stopShimmer()
    }

    private fun ShimmerSearchItemBinding.setupMargins() {
        val params: ViewGroup.MarginLayoutParams =
            root.layoutParams as ViewGroup.MarginLayoutParams
        when (index % 2) {
            0 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium)
            )
            1 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium)
            )
        }
        root.layoutParams = params
    }
}