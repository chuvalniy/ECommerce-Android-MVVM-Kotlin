package com.example.ecommercialapplication.feature_main.di

import com.example.ecommercialapplication.feature_main.data.remote.ShopApi
import com.example.ecommercialapplication.feature_main.data.repository.ShopRepositoryImpl
import com.example.ecommercialapplication.feature_main.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShopModule {

    @Provides
    @Singleton
    fun provideShopApi(): ShopApi {
        return Retrofit.Builder()
            .baseUrl(ShopApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopApi::class.java)
    }

    @Provides
    @Singleton
    fun provideShopRepository(api: ShopApi): ShopRepository {
        return ShopRepositoryImpl(api)
    }
}