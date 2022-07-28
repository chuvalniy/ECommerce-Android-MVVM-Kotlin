package com.example.feature_main_screen.di

import com.example.feature_main_screen.presentation.home_screen.view_model.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeScreenPresentationModule = module {
    viewModel {
        HomeScreenViewModel(get())
    }
}