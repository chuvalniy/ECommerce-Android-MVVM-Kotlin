package com.example.feature_home.di

import com.example.feature_home.presentation.home_screen.view_model.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeScreenPresentationModule = module {
    viewModel {
        HomeScreenViewModel(get())
    }
}