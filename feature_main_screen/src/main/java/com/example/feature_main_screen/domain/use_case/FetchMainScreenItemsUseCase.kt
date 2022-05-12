package com.example.feature_main_screen.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.model.MainScreenDomain
import com.example.feature_main_screen.domain.repository.MainScreenRepository

class FetchMainScreenItemsUseCase(
    private val repository: MainScreenRepository
) {

    suspend operator fun invoke(): Resource<MainScreenDomain> {
        return repository.fetchMainScreenItems()
    }
}