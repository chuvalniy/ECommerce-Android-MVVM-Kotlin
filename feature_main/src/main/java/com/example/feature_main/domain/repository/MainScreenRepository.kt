package com.example.feature_main.domain.repository

import com.example.core.utils.Resource
import com.example.feature_main.domain.model.DomainDataSource

interface MainScreenRepository {

    suspend fun fetchData(): Resource<DomainDataSource>
}