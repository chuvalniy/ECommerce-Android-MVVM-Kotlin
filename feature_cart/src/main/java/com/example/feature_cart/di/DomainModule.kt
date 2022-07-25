package com.example.feature_cart.di

import com.example.feature_cart.domain.use_case.FetchCartInfoUseCase
import org.koin.dsl.module


val cartDomainModule = module {
    factory {
        FetchCartInfoUseCase(get())
    }
}