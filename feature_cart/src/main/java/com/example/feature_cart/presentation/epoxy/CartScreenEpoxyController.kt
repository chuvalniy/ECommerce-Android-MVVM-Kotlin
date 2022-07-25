package com.example.feature_cart.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_cart.presentation.epoxy.models.CartItemEpoxyModel
import com.example.feature_cart.presentation.epoxy.models.CheckoutEpoxyModel
import com.example.feature_cart.presentation.epoxy.models.ShimmerCartItemEpoxyModel
import com.example.feature_cart.presentation.view_model.CartScreenState

class CartScreenEpoxyController(
    private val glide: RequestManager,
    private val onCheckoutButtonClick: () -> Unit
) : TypedEpoxyController<CartScreenState>() {

    override fun buildModels(state: CartScreenState?) {

        if (state?.isLoading == true) {
            repeat(4) {
                ShimmerCartItemEpoxyModel().id("shimmer_cart_item_$it").addTo(this)
            }
        } else if (state?.isLoading == false) {
            state.cartInfo.basket.forEach { item ->
                CartItemEpoxyModel(item, glide).id(item.id).addTo(this)
            }

            CheckoutEpoxyModel(state.cartInfo.total, state.cartInfo.delivery, onCheckoutButtonClick)
                .id("cart_checkout")
                .addTo(this)
        }
    }


}
