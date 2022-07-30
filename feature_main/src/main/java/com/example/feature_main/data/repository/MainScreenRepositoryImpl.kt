package com.example.feature_main.data.repository

import com.example.core.utils.Resource
import com.example.feature_main.data.mapper.toDomainDataSource
import com.example.feature_main.data.remote.MainScreenApi
import com.example.feature_main.domain.model.DomainDataSource
import com.example.feature_main.domain.repository.MainScreenRepository

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