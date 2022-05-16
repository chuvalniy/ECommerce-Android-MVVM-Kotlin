package com.example.feature_main_screen.presentation.main_screen.adapters

import com.bumptech.glide.RequestManager
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ItemBestSellerBinding
import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenAdapterDelegate {

    fun bestSellerAdapterDelegate(
        glide: RequestManager,
        onGoToDetails: (BestSellerDomain) -> Unit
    ) = adapterDelegateViewBinding<BestSellerDomain, DisplayableItem, ItemBestSellerBinding>(
        { layoutInflater, parent -> ItemBestSellerBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.tvDefaultPrice.text = this.itemView.context.getString(
                R.string.price_without_discount,
                item.price_without_discount
            )
            binding.tvDiscountPrice.text = this.itemView.context.getString(
                R.string.discount_price,
                item.discount_price
            )
            binding.tvTitle.text = item.title
            binding.btnAddToFavorites.isChecked = item.is_favorites

            binding.cvBestSeller.setOnClickListener {
                onGoToDetails(item)
            }

            glide.load(item.picture).into(binding.ivBestSeller)
        }
    }

}