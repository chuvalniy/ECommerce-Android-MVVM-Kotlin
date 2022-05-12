package com.example.feature_main_screen.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.model.BasketDomain
import com.example.feature_main_screen.domain.repository.MainScreenRepository

class FetchBasketInfoUseCase(
    private val repository: MainScreenRepository
) {

    suspend operator fun invoke(): Resource<List<BasketDomain>> {
        return repository.fetchCartInfo()
    }
}