package com.example.feature_main_screen.di

import androidx.room.Room
import com.example.core.utils.Constants
import com.example.core.utils.parser.GsonParser
import com.example.feature_main_screen.data.local.MainScreenDatabase
import com.example.feature_main_screen.data.local.utils.Converters
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.data.repository.MainScreenRepositoryImpl
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
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

    single {
        Room.databaseBuilder(
            androidContext(),
            MainScreenDatabase::class.java,
            MainScreenDatabase.DATABASE_NAME
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    single<MainScreenRepository> {
        MainScreenRepositoryImpl(
            api = get(),
            db = get()
        )
    }
}