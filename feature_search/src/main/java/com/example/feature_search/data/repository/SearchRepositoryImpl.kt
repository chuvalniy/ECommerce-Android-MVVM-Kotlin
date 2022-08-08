package com.example.feature_search.data.repository

import androidx.room.withTransaction
import com.example.core.utils.Resource
import com.example.feature_search.data.local.SearchDatabase
import com.example.feature_search.data.mapper.toCacheDataSource
import com.example.feature_search.data.mapper.toDomainDataSource
import com.example.feature_search.data.remote.SearchFirestore
import com.example.feature_search.domain.model.DomainDataSource
import com.example.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val api: SearchFirestore,
    private val db: SearchDatabase
) : SearchRepository {

    private val dao = db.dao

    override fun searchData(): Flow<Resource<List<DomainDataSource>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val cache = dao.fetchCache()

        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.map { it.toDomainDataSource() }))
            emit(Resource.Loading(isLoading = false))
        }

        val response = try {
            api.fetchData()
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
            null
        }

        response?.let { data ->
            db.withTransaction {
                dao.clearCache(data.map { it.title })
                dao.insertCache(data.map { it.toCacheDataSource() })
            }

            emit(Resource.Success(dao.fetchCache().map { it.toDomainDataSource() }))
            emit(Resource.Loading(isLoading = false))
        }
    }
}