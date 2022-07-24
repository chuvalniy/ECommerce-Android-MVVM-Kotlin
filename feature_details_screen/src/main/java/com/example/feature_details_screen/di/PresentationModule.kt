package com.example.feature_details_screen.di

import com.example.feature_details_screen.presentation.view_model.DetailsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsPresentationModule = module {

    viewModel {
        DetailsScreenViewModel(get())
    }

}