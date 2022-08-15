package com.example.feature_search.di

import androidx.room.Room
import com.example.feature_search.data.local.SearchDatabase
import com.example.feature_search.data.remote.SearchFirestore
import com.example.feature_search.data.remote.SearchFirestoreImpl
import com.example.feature_search.data.repository.SearchRepositoryImpl
import com.example.feature_search.domain.repository.SearchRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val searchDataModule = module {
    single<SearchFirestore> {
        SearchFirestoreImpl()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            SearchDatabase::class.java,
            SearchDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<SearchRepository> {
        SearchRepositoryImpl(
            get(), get()
        )
    }
}