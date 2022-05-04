package com.example.ecommercialapplication.feature_main.presentation.main_screen.fragment

import com.example.ecommercialapplication.feature_main.domain.model.MainScreenDomain

sealed class MainScreenEvent {
    class Success(val data: MainScreenDomain) : MainScreenEvent()
    class Failure(val error: String) : MainScreenEvent()
    object Loading : MainScreenEvent()
    object Empty : MainScreenEvent()
}
