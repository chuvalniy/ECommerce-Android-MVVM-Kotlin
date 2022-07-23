package com.example.feature_main_screen.presentation.main_screen.epoxy

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.BestSellerItemBinding
import com.example.feature_main_screen.domain.model.BestSellerDomain

data class BestSellerEpoxyModel(
    val bestSeller: BestSellerDomain,
    val glide: RequestManager
) : ViewBindingKotlinModel<BestSellerItemBinding>(R.layout.best_seller_item) {

    override fun BestSellerItemBinding.bind() {
        tvDefaultPrice.text = root.context.getString(
            R.string.price_without_discount,
            bestSeller.price_without_discount
        )
        tvDiscountPrice.text = root.context.getString(
            R.string.discount_price,
            bestSeller.discount_price
        )
        tvTitle.text = bestSeller.title
        btnAddToFavorites.isChecked = bestSeller.is_favorites

//        cvBestSeller.setOnClickListener {
//            onGoToDetails(item)
//        }

        glide.load(bestSeller.picture).into(ivBestSeller)
    }
}