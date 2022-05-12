package com.example.feature_main_screen.presentation.main_screen.utils

import com.example.feature_main_screen.domain.model.MainScreenDomain

sealed class MainScreenEvent {
    class Success(val data: MainScreenDomain) : MainScreenEvent()
    class Failure(val error: String) : MainScreenEvent()
    object Loading : MainScreenEvent()
    object Empty : MainScreenEvent()
}
