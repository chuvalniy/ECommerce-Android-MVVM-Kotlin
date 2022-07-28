package com.example.feature_details_screen.di

import com.example.feature_details_screen.domain.use_case.FetchDetailsScreenDataSource
import org.koin.dsl.module

val detailsDomainModule = module {
    factory {
        FetchDetailsScreenDataSource(get())
    }
}