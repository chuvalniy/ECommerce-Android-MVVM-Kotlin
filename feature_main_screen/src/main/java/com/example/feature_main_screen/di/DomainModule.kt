package com.example.feature_main_screen.di

import com.example.feature_main_screen.domain.use_case.FetchMainScreenDataSource
import org.koin.dsl.module


val mainScreenDomainModule = module {
    factory {
        FetchMainScreenDataSource(get())
    }
}