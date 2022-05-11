package com.example.feature_cart.data.repository

import com.example.core.utils.Resource
import com.example.feature_cart.data.mapper.toCartDomain
import com.example.feature_cart.data.remote.CartScreenApi
import com.example.feature_cart.domain.model.CartDomain
import com.example.feature_cart.domain.repository.CartScreenRepository
import java.lang.Exception

class CartScreenRepositoryImpl(
    private val api: CartScreenApi
) : CartScreenRepository {

    override suspend fun fetchCartInfo(): Resource<CartDomain> {
        return try {
            val remoteData = api.fetchCartInfo().toCartDomain()
            Resource.Success(remoteData)
        } catch (e: Exception) {
            Resource.Error(error = e.message)
        } catch (e: Exception) {
            Resource.Error(error = e.message)
        }
    }
}