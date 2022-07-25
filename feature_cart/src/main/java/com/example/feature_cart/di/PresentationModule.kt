package com.example.feature_cart.di

import com.example.core.utils.Constants
import com.example.feature_cart.data.remote.CartScreenApi
import com.example.feature_cart.data.repository.CartScreenRepositoryImpl
import com.example.feature_cart.domain.repository.CartScreenRepository
import com.example.feature_cart.domain.use_case.FetchCartInfoUseCase
import com.example.feature_cart.presentation.view_model.CartScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val cartPresentationModule = module {
    viewModel {
        CartScreenViewModel(get())
    }
}