package com.example.feature_main_screen.di

import com.example.feature_main_screen.domain.use_case.FetchDataUseCase
import org.koin.dsl.module


val homeScreenDomainModule = module {
    factory {
        FetchDataUseCase(get())
    }
}