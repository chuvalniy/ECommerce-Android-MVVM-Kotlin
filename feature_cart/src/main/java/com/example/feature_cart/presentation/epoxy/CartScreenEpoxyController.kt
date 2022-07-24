package com.example.feature_cart.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_cart.domain.model.BasketDomain
import com.example.feature_cart.domain.model.CartDomain
import com.example.feature_cart.presentation.epoxy.models.*

class CartScreenEpoxyController(
    private val glide: RequestManager
) : TypedEpoxyController<CartDomain>() {

    override fun buildModels(data: CartDomain?) {

        data?.let { cart ->
            cart.basket.forEach { item ->
                CartItemEpoxyModel(item, glide).id(item.id).addTo(this)
            }

            CheckoutEpoxyModel(cart.total, cart.delivery).id("cart_checkout").addTo(this)
        }
    }
}