package com.example.feature_cart.presentation.epoxy.models

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_cart.R
import com.example.feature_cart.databinding.ModelCheckoutBinding

data class CheckoutEpoxyModel(
    val totalPrice: String,
    val deliveryPrice: String,
): ViewBindingKotlinModel<ModelCheckoutBinding>(R.layout.model_checkout) {

    override fun ModelCheckoutBinding.bind() {
        tvTotal.text = root.context.getString(R.string.total_price, totalPrice)
        tvDelivery.text = deliveryPrice
    }
}