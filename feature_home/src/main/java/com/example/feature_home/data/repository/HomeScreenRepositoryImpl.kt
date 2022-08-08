package com.example.feature_home.data.repository

import androidx.room.withTransaction
import com.example.core.utils.Resource
import com.example.feature_home.data.local.HomeScreenDatabase
import com.example.feature_home.data.mapper.toCacheDataSource
import com.example.feature_home.data.mapper.toDomainDataSource
import com.example.feature_home.data.remote.HomeFirestore
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeScreenRepositoryImpl(
    private val api: HomeFirestore,
    private val db: HomeScreenDatabase
) : HomeScreenRepository {

    private val dao = db.dao

    override fun fetchData(
        selectedCategory: Int
    ): Flow<Resource<List<DomainDataSource>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val cache = dao.fetchCache(selectedCategory)

        if (cache.isNotEmpty()) {

            emit(Resource.Success(cache.map { it.toDomainDataSource() }))
            emit(Resource.Loading(isLoading = false))
        }


        val response = try {
            api.fetchCloudDataSource(selectedCategory)
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
            null
        }

        response?.let { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.map { it.toCacheDataSource() })
            }

            emit(Resource.Success(
                dao.fetchCache(selectedCategory).map { it.toDomainDataSource() }
            ))
            emit(Resource.Loading(isLoading = false))
        }
    }
}