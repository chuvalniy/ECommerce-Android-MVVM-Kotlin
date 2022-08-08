package com.example.feature_home.di

import com.example.feature_home.domain.use_case.FetchDataUseCase
import com.example.feature_home.domain.use_case.FilterBestSellerDomain
import com.example.feature_home.domain.use_case.FilterHotSalesUseCase
import org.koin.dsl.module


val homeScreenDomainModule = module {
    factory {
        FetchDataUseCase(get())
    }
    factory {
        FilterBestSellerDomain()
    }
    factory {
        FilterHotSalesUseCase()
    }
}