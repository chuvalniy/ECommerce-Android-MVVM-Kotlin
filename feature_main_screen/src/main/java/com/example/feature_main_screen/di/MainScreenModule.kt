package com.example.feature_main_screen.di

import com.example.core.utils.Constants
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.data.repository.MainScreenRepositoryImpl
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import com.example.feature_main_screen.domain.use_case.FetchMainScreenItemsUseCase
import com.example.feature_main_screen.presentation.main_screen.view_model.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val mainScreenModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainScreenApi::class.java)
    }
    single<MainScreenRepository> {
        MainScreenRepositoryImpl(get())
    }
    single {
        FetchMainScreenItemsUseCase(get())
    }

    viewModel {
        MainScreenViewModel(get())
    }
}