package com.example.feature_main_screen.presentation.main_screen.adapters

import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ItemBestSellerBinding
import com.example.feature_main_screen.databinding.ItemHotSalesBinding
import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HomeStoreDomain
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

    fun hotSalesAdapterDelegate(
        glide: RequestManager
    ) = adapterDelegateViewBinding<HomeStoreDomain, DisplayableItem, ItemHotSalesBinding>(
        { layoutInflater, parent -> ItemHotSalesBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.tvTitleHomeStore.text = item.title
            binding.tvSubtitleHomeScreen.text = item.subtitle
            binding.btnBuyNow.isVisible = item.is_buy

            item.is_new?.let { isNew ->
                binding.tvNew.isVisible = isNew
            }

            glide.load(item.picture).into(binding.ivHotSales)
        }
    }
}