package com.example.feature_cart.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.core.ui.DisplayableItem
import com.example.feature_cart.R
import com.example.feature_cart.databinding.CartItemBinding
import com.example.feature_cart.domain.model.BasketDomain
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object CartScreenAdapterDelegate {

    fun basketAdapterDelegate(glide: RequestManager) =
        adapterDelegateViewBinding<BasketDomain, DisplayableItem, CartItemBinding>(
            { layoutInflater, parent ->  CartItemBinding.inflate(layoutInflater, parent, false) }
        ) {
            bind {
                binding.tvPrice.text = this.itemView.context.getString(R.string.product_price, item.price)
                binding.tvTitle.text = item.title
                glide.load(item.images).into(binding.ivCartProduct)
            }
        }
}