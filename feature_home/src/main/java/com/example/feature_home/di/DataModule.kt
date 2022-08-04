package com.example.feature_home.di

import androidx.room.Room
import com.example.core.utils.Constants
import com.example.core.utils.parser.GsonParser
import com.example.feature_home.data.local.HomeScreenDatabase
import com.example.feature_home.data.local.utils.Converters
import com.example.feature_home.data.remote.HomeScreenApi
import com.example.feature_home.data.repository.HomeScreenRepositoryImpl
import com.example.feature_home.domain.repository.HomeScreenRepository
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val homeScreenDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeScreenApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            HomeScreenDatabase::class.java,
            HomeScreenDatabase.DATABASE_NAME
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    single<HomeScreenRepository> {
        HomeScreenRepositoryImpl(
            api = get(),
            db = get()
        )
    }
}