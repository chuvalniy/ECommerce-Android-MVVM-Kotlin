package com.example.feature_home.di

import androidx.room.Room
import com.example.feature_home.data.local.HomeScreenDatabase
import com.example.feature_home.data.remote.HomeFirestore
import com.example.feature_home.data.remote.HomeFirestoreImpl
import com.example.feature_home.data.repository.HomeScreenRepositoryImpl
import com.example.feature_home.domain.repository.HomeScreenRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val homeScreenDataModule = module {
    single<HomeFirestore> {
        HomeFirestoreImpl()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            HomeScreenDatabase::class.java,
            HomeScreenDatabase.DATABASE_NAME
        )
            .build()
    }

    single<HomeScreenRepository> {
        HomeScreenRepositoryImpl(
            api = get(),
            db = get()
        )
    }
}