package com.example.feature_main_screen.presentation.main_screen.utils

import com.example.feature_main_screen.domain.model.BasketDomain

sealed class CartScreenEvent {
    class Success(val data: List<BasketDomain>) : CartScreenEvent()
    class Failure(val error: String) : CartScreenEvent()
    object Loading : CartScreenEvent()
    object Empty : CartScreenEvent()
}