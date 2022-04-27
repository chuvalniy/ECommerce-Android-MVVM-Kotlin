package com.example.ecommercialapplication.feature_main.di

import com.example.ecommercialapplication.feature_main.data.remote.MainScreenApi
import com.example.ecommercialapplication.feature_main.data.repository.MainScreenRepositoryImpl
import com.example.ecommercialapplication.feature_main.domain.repository.MainScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainScreenModule {

    @Provides
    @Singleton
    fun provideMainScreenApi(): MainScreenApi {
        return Retrofit.Builder()
            .baseUrl(MainScreenApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainScreenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMainScreenRepository(api: MainScreenApi): MainScreenRepository {
        return MainScreenRepositoryImpl(api)
    }
}