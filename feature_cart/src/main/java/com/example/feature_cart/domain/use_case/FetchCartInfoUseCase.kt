package com.example.feature_cart.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_cart.domain.model.CartDomain
import com.example.feature_cart.domain.repository.CartScreenRepository

class FetchCartInfoUseCase(
    private val repository: CartScreenRepository
) {

    suspend operator fun invoke() = repository.fetchCartInfo()
}