package com.example.feature_details_screen.di

import com.example.feature_details_screen.domain.use_case.FetchDataUseCase
import org.koin.dsl.module

val detailsDomainModule = module {
    factory {
        FetchDataUseCase(get())
    }
}