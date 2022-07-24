package com.example.feature_main_screen.di

import com.example.core.utils.Constants
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.data.repository.MainScreenRepositoryImpl
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainScreenDataModule = module {
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
}