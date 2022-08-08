package com.example.feature_search.di

import com.example.feature_search.presentation.view_model.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchPresentationModule = module {
    viewModel {
        SearchViewModel(get())
    }
}