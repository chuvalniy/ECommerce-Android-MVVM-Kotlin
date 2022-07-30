package com.example.feature_main.di

import com.example.core.utils.Constants
import com.example.feature_main.data.remote.MainScreenApi
import com.example.feature_main.data.repository.MainScreenRepositoryImpl
import com.example.feature_main.domain.repository.MainScreenRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainScreenApi::class.java)
    }

    single<MainScreenRepository> {
        MainScreenRepositoryImpl(api = get())
    }
}