package com.example.feature_cart.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.core.utils.Resource
import com.example.feature_cart.data.local.CartDatabase
import com.example.feature_cart.data.mapper.toCartDomain
import com.example.feature_cart.data.mapper.toCartEntity
import com.example.feature_cart.data.remote.CartScreenApi
import com.example.feature_cart.domain.model.CartDomain
import com.example.feature_cart.domain.repository.CartScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartScreenRepositoryImpl(
    private val api: CartScreenApi,
    private val db: CartDatabase
) : CartScreenRepository {

    private val dao = db.dao

    override fun fetchCartInfo(): Flow<Resource<CartDomain>> = flow {
        emit(Resource.Loading(isLoading = true))

        val cache = dao.fetchFromCache()

        if (cache != null) {
            emit(Resource.Success(data = cache.toCartDomain()))
            emit(Resource.Loading(isLoading = false))
        }

        val response = try {
            api.fetchCartInfo()
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
                dao.insertIntoCache(data.toCartEntity())
            }
            emit(Resource.Success(data = dao.fetchFromCache()?.toCartDomain()))
            emit(Resource.Loading(isLoading = false))
        }
    }
}