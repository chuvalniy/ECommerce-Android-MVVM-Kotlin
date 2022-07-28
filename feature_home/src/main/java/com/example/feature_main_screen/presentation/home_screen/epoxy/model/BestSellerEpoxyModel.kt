package com.example.feature_main_screen.presentation.home_screen.epoxy.model

import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.BestSellerItemBinding
import com.example.feature_main_screen.domain.model.BestSellerDomain

data class BestSellerEpoxyModel(
    val bestSeller: BestSellerDomain,
    val index: Int,
    val onProductClick: () -> Unit,
    val glide: RequestManager
) : ViewBindingKotlinModel<BestSellerItemBinding>(R.layout.best_seller_item) {

    override fun BestSellerItemBinding.bind() {
        setupMargins()

        tvDefaultPrice.text = root.context.getString(
            R.string.best_selelr_price_without_discount,
            bestSeller.priceWithoutDiscount
        )
        tvDiscountPrice.text = root.context.getString(
            R.string.best_seller_discount_price,
            bestSeller.discountPrice
        )
        tvTitle.text = bestSeller.title
        btnAddToFavorites.isChecked = bestSeller.isFavorites

        cvBestSeller.setOnClickListener { onProductClick() }

        glide.load(bestSeller.picture).into(ivBestSeller)
    }

    private fun BestSellerItemBinding.setupMargins() {
        val params: ViewGroup.MarginLayoutParams = root.layoutParams as ViewGroup.MarginLayoutParams
        when (index % 2) {
            0 -> params.setMargins(
                0,
                0,
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_horizontal),
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_bottom)
            )
            1 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_horizontal),
                0,
                0,
                root.context.resources.getDimensionPixelSize(R.dimen.best_seller_card_margin_bottom)
            )
        }
        root.layoutParams = params
    }
}