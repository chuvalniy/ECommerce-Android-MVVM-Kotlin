package com.example.feature_cart.presentation.view_model

import com.example.feature_cart.domain.model.CartDomain

data class CartScreenState(
    val cartInfo: CartDomain = CartDomain(
        basket = emptyList(),
        delivery = "",
        id = "",
        total = ""
    ),
    val isLoading: Boolean = false
)