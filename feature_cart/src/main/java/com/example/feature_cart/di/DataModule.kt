package com.example.feature_cart.di

import com.example.core.utils.Constants
import com.example.feature_cart.data.remote.CartScreenApi
import com.example.feature_cart.data.repository.CartScreenRepositoryImpl
import com.example.feature_cart.domain.repository.CartScreenRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val cartDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartScreenApi::class.java)
    }
    single<CartScreenRepository> {
        CartScreenRepositoryImpl(get())
    }

}