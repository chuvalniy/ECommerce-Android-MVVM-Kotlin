package com.example.feature_cart.domain.use_case

import com.example.feature_cart.domain.repository.CartScreenRepository

class FetchCartInfoUseCase(
    private val repository: CartScreenRepository
) {

    operator fun invoke() = repository.fetchCartInfo()
}