package com.example.feature_cart.domain.repository

import com.example.core.utils.Resource
import com.example.feature_cart.domain.model.CartDomain
import kotlinx.coroutines.flow.Flow

interface CartScreenRepository {

    fun fetchCartInfo(): Flow<Resource<CartDomain>>
}