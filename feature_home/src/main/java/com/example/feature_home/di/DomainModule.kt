package com.example.feature_home.di

import com.example.feature_home.domain.use_case.FetchDataUseCase
import org.koin.dsl.module


val homeScreenDomainModule = module {
    factory {
        FetchDataUseCase(get())
    }
}