package com.example.feature_details_screen.di

import com.example.core.utils.Constants
import com.example.feature_details_screen.data.remote.DetailsScreenApi
import com.example.feature_details_screen.data.repository.DetailsScreenRepositoryImpl
import com.example.feature_details_screen.domain.repository.DetailsScreenRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val detailsDataModule = module {
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
}