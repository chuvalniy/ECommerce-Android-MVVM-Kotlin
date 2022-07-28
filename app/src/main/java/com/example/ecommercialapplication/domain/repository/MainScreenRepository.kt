package com.example.ecommercialapplication.domain.repository

import com.example.core.utils.Resource
import com.example.ecommercialapplication.domain.model.DomainDataSource

interface MainScreenRepository {

    suspend fun fetchData(): Resource<DomainDataSource>
}