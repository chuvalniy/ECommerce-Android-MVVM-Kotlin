package com.example.feature_cart.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_cart.domain.model.CartDomain
import com.example.feature_cart.presentation.epoxy.models.CartItemEpoxyModel
import com.example.feature_cart.presentation.epoxy.models.CheckoutEpoxyModel

class CartScreenEpoxyController(
    private val glide: RequestManager,
    private val onCheckoutButtonClick: () -> Unit
) : TypedEpoxyController<CartDomain>() {

    override fun buildModels(data: CartDomain?) {

        data?.let { cart ->
            val cartBasket= cart.basket + cart.basket + cart.basket + cart.basket
            cartBasket.forEach { item ->
                CartItemEpoxyModel(item, glide).id(item.id).addTo(this)
            }

            CheckoutEpoxyModel(cart.total, cart.delivery, onCheckoutButtonClick)
                .id("cart_checkout")
                .addTo(this)
        }
    }
}