package com.example.ecommercialapplication.data.repository

import com.example.core.utils.Resource
import com.example.ecommercialapplication.data.mapper.toDomainDataSource
import com.example.ecommercialapplication.data.remote.MainScreenApi
import com.example.ecommercialapplication.domain.model.DomainDataSource
import com.example.ecommercialapplication.domain.repository.MainScreenRepository

class MainScreenRepositoryImpl(
    private val api: MainScreenApi,
) : MainScreenRepository {

    override suspend fun fetchData(): Resource<DomainDataSource> {
        return try {
            Resource.Success(api.fetchCloudDataSource().toDomainDataSource())
        } catch (e: Exception) {
            Resource.Error(error = e.message)
        }
    }
}