package com.example.feature_search.di

import com.example.feature_search.domain.use_case.SearchDataUseCase
import org.koin.dsl.module

val searchDomainModule = module {
    factory {
        SearchDataUseCase(get())
    }
}