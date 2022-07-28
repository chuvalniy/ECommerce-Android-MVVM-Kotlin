package com.example.ecommercialapplication.data.di

import com.example.core.utils.Constants
import com.example.ecommercialapplication.data.remote.MainScreenApi
import com.example.ecommercialapplication.data.repository.MainScreenRepositoryImpl
import com.example.ecommercialapplication.domain.repository.MainScreenRepository
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