package com.example.feature_main.di

import com.example.feature_main.presentation.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainPresentationModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
}