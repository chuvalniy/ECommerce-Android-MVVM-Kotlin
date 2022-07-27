package com.example.feature_cart.di

import androidx.room.Room
import com.example.core.utils.Constants
import com.example.core.utils.parser.GsonParser
import com.example.feature_cart.data.local.CartDatabase
import com.example.feature_cart.data.local.utils.Converters
import com.example.feature_cart.data.remote.CartScreenApi
import com.example.feature_cart.data.repository.CartScreenRepositoryImpl
import com.example.feature_cart.domain.repository.CartScreenRepository
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
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
    single {
        Room.databaseBuilder(
            androidContext(),
            CartDatabase::class.java,
            CartDatabase.DATABASE_NAME
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


    single<CartScreenRepository> {
        CartScreenRepositoryImpl(
            api = get(),
            db = get()
        )
    }

}