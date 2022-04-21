package com.example.ecommercialapplication.feature_main.presentation

import com.example.ecommercialapplication.feature_main.domain.model.ShopItem

sealed class MainScreenEvent {
    class Success(val data: ShopItem) : MainScreenEvent()
    class Failure(val error: String) : MainScreenEvent()
    object Loading : MainScreenEvent()
    object Empty : MainScreenEvent()
}
