package com.example.feature_cart.presentation.utils

import com.example.feature_cart.domain.model.CartDomain

sealed class CartScreenEvent {
    class Success(val data: CartDomain) : CartScreenEvent()
    class Failure(val error: String) : CartScreenEvent()
    object Loading : CartScreenEvent()
    object Empty : CartScreenEvent()
}