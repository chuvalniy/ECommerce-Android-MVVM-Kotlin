package com.example.feature_home.presentation.home_screen.epoxy.model

import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.BestSellerItemBinding
import com.example.feature_home.domain.model.BestSellerDomain
import com.example.feature_home.domain.model.DomainDataSource

data class BestSellerEpoxyModel(
    val bestSeller: BestSellerDomain,
    val index: Int,
    val onProductClick: (String) -> Unit,
    val glide: RequestManager
) : ViewBindingKotlinModel<BestSellerItemBinding>(R.layout.best_seller_item) {

    override fun BestSellerItemBinding.bind() {
        setupMargins()

        tvPrice.text = root.context.getString(
            R.string.best_seller_discount_price,
            bestSeller.price
        )
        tvTitle.text = bestSeller.title

        cvBestSeller.setOnClickListener { onProductClick(bestSeller.id) }

        glide.load(bestSeller.image).into(ivBestSeller)
    }

    private fun BestSellerItemBinding.setupMargins() {
        val params: ViewGroup.MarginLayoutParams =
            cvBestSeller.layoutParams as ViewGroup.MarginLayoutParams
        when (index % 2) {
            0 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.home_padding),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_top),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_horizontal),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_bottom)
            )
            1 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_horizontal),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_top),
                root.context.resources.getDimensionPixelSize(R.dimen.home_padding),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_bottom)
            )
        }
        cvBestSeller.layoutParams = params
    }
}