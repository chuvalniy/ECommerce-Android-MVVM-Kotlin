package com.example.feature_main_screen.presentation.main_screen.view_model.model

sealed class CartScreenEvent {
    class Success(
        val numberOfItems: String,
        val shouldShowBadge: Boolean
    ) : CartScreenEvent()
    class Failure(val error: String) : CartScreenEvent()
    object Loading : CartScreenEvent()
    object Empty : CartScreenEvent()
}