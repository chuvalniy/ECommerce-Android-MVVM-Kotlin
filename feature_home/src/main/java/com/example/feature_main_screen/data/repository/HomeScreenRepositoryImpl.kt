package com.example.feature_main_screen.data.repository

import androidx.room.withTransaction
import com.example.core.utils.Resource
import com.example.feature_main_screen.data.local.HomeScreenDatabase
import com.example.feature_main_screen.data.mapper.*
import com.example.feature_main_screen.data.remote.HomeScreenApi
import com.example.feature_main_screen.domain.model.DomainDataSource
import com.example.feature_main_screen.domain.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class HomeScreenRepositoryImpl(
    private val api: HomeScreenApi,
    private val db: HomeScreenDatabase
) : HomeScreenRepository {

    private val dao = db.dao

    override fun fetchData(): Flow<Resource<DomainDataSource>> = flow {
        emit(Resource.Loading(isLoading = true))

        val cache = dao.fetchCache()

        if (cache != null) {
            emit(Resource.Success(cache.toDomainDataSource()))
            emit(Resource.Loading(isLoading = false))
        }

        val response = try {
            api.fetchCloudDataSource()
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message))
            null
        } catch (e: HttpException) {
            emit(Resource.Error(error = e.message))
            null
        }

        response?.let { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.toCacheDataSource())
            }
            emit(Resource.Success(dao.fetchCache()?.toDomainDataSource()))
            emit(Resource.Loading(isLoading = false))
        }
    }
}