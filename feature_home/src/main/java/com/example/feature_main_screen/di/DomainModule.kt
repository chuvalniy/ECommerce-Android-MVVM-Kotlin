package com.example.feature_main_screen.di

import com.example.feature_main_screen.domain.use_case.FetchDomainDataSource
import org.koin.dsl.module


val homeScreenDomainModule = module {
    factory {
        FetchDomainDataSource(get())
    }
}