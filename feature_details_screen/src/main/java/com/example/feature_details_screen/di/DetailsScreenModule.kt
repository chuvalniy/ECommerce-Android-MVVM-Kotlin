package com.example.feature_details_screen.di

import com.example.core.utils.Constants
import com.example.feature_details_screen.data.remote.DetailsScreenApi
import com.example.feature_details_screen.data.repository.DetailsScreenRepositoryImpl
import com.example.feature_details_screen.domain.repository.DetailsScreenRepository
import com.example.feature_details_screen.domain.use_case.FetchProductDetailsUseCase
import com.example.feature_details_screen.presentation.view_model.DetailsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val detailsScreenModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DetailsScreenApi::class.java)
    }
    single<DetailsScreenRepository> {
        DetailsScreenRepositoryImpl(get())
    }
    viewModel {
        DetailsScreenViewModel(get())
    }
    single {
        FetchProductDetailsUseCase(get())
    }
}