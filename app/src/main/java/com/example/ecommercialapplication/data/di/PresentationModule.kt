package com.example.ecommercialapplication.data.di

import com.example.ecommercialapplication.presentation.view_model.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainPresentationModule = module {
    viewModel {
        MainScreenViewModel(repository = get())
    }
}