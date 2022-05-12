package com.example.feature_cart.domain.repository

import com.example.core.utils.Resource
import com.example.feature_cart.domain.model.CartDomain

interface CartScreenRepository {

    suspend fun fetchCartInfo(): Resource<CartDomain>
}