package com.example.feature_cart.presentation.epoxy.models

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_cart.R
import com.example.feature_cart.databinding.CartItemBinding
import com.example.feature_cart.domain.model.BasketDomain

data class CartItemEpoxyModel(
    val basketDomain: BasketDomain,
    val glide: RequestManager
): ViewBindingKotlinModel<CartItemBinding>(R.layout.cart_item) {

    override fun CartItemBinding.bind() {
        tvPrice.text = root.context.getString(R.string.product_price, basketDomain.price)
        tvTitle.text = basketDomain.title
        glide.load(basketDomain.images).into(ivCartProduct)
    }
}