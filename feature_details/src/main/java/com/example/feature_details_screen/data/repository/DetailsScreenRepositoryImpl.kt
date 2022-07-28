package com.example.feature_details_screen.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.core.utils.Resource
import com.example.feature_details_screen.data.local.DetailsScreenDatabase
import com.example.feature_details_screen.data.mapper.toProductDetailsDomain
import com.example.feature_details_screen.data.mapper.toProductDetailsEntity
import com.example.feature_details_screen.data.remote.DetailsScreenApi
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.domain.repository.DetailsScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailsScreenRepositoryImpl(
    private val api: DetailsScreenApi,
    private val db: DetailsScreenDatabase
) : DetailsScreenRepository {

    private val dao = db.dao

    override fun fetchProductDetails(): Flow<Resource<ProductDetailsDomain>> = flow {
        emit(Resource.Loading(isLoading = true))

        val cache = dao.fetchCache()

        Log.d("TAGTAG", cache.toString())

        if (cache != null) {
            emit(Resource.Success(cache.toProductDetailsDomain()))
            emit(Resource.Loading(isLoading = false))
        }

        val response = try {
            api.fetchProductDetails()
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
            null
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
            null
        }

        response?.let { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.toProductDetailsEntity())
            }
            emit(Resource.Success(dao.fetchCache()?.toProductDetailsDomain()))
            emit(Resource.Loading(isLoading = false))
        }
    }
}