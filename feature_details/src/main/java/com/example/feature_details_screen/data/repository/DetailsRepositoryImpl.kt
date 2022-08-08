package com.example.feature_details_screen.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.core.utils.Resource
import com.example.feature_details_screen.data.local.DetailsDatabase
import com.example.feature_details_screen.data.mapper.toCacheDataSource
import com.example.feature_details_screen.data.mapper.toDomainDataSource
import com.example.feature_details_screen.data.remote.DetailsFirestore
import com.example.feature_details_screen.domain.model.DomainDataSource
import com.example.feature_details_screen.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailsRepositoryImpl(
    private val api: DetailsFirestore,
    private val db: DetailsDatabase
) : DetailsRepository {

    private val dao = db.dao

    override fun fetchData(id: String): Flow<Resource<DomainDataSource>> = flow {
        emit(Resource.Loading(isLoading = true))

        Log.d("TAGTAG", "repo $id")

        val cache = dao.fetchCache(id)

        if (cache != null) {
            emit(Resource.Success(cache.toDomainDataSource()))
            emit(Resource.Loading(isLoading = false))
        }

        val response = try {
            api.fetchCloudDataSource(id)
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
            null
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
            null
        }

        response?.let { data ->
            Log.d("TAGTAG", "test $data")
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.toCacheDataSource())
            }
            emit(Resource.Success(dao.fetchCache(id)?.toDomainDataSource()))
            emit(Resource.Loading(isLoading = false))
        }
    }
}