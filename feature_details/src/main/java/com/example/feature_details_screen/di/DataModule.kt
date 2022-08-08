package com.example.feature_details_screen.di

import androidx.room.Room
import com.example.core.utils.parser.GsonParser
import com.example.feature_details_screen.data.local.DetailsDatabase
import com.example.feature_details_screen.data.local.utils.Converters
import com.example.feature_details_screen.data.remote.DetailsFirestore
import com.example.feature_details_screen.data.remote.DetailsFirestoreImpl
import com.example.feature_details_screen.data.repository.DetailsRepositoryImpl
import com.example.feature_details_screen.domain.repository.DetailsRepository
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val detailsDataModule = module {

    single<DetailsFirestore> {
        DetailsFirestoreImpl()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            DetailsDatabase::class.java,
            DetailsDatabase.DATABASE_NAME
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    single<DetailsRepository> {
        DetailsRepositoryImpl(
            api = get(),
            db = get()
        )
    }
}