package com.example.feature_details_screen.di

import androidx.room.Room
import com.example.core.utils.Constants
import com.example.core.utils.parser.GsonParser
import com.example.feature_details_screen.data.local.DetailsScreenDatabase
import com.example.feature_details_screen.data.local.utils.Converters
import com.example.feature_details_screen.data.remote.DetailsScreenApi
import com.example.feature_details_screen.data.repository.DetailsScreenRepositoryImpl
import com.example.feature_details_screen.domain.repository.DetailsScreenRepository
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
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

    single {
        Room.databaseBuilder(
            androidContext(),
            DetailsScreenDatabase::class.java,
            DetailsScreenDatabase.DATABASE_NAME
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    single<DetailsScreenRepository> {
        DetailsScreenRepositoryImpl(
            api = get(),
            db = get()
        )
    }
}