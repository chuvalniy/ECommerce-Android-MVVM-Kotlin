package com.example.feature_home.domain.use_case

import com.example.feature_home.domain.repository.HomeScreenRepository

class FetchDataUseCase(
    private val repository: HomeScreenRepository
) {

    fun execute() = repository.fetchData()

}