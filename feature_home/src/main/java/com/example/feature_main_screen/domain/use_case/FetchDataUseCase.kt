package com.example.feature_main_screen.domain.use_case

import com.example.feature_main_screen.domain.repository.HomeScreenRepository

class FetchDataUseCase(
    private val repository: HomeScreenRepository
) {

    fun execute() = repository.fetchData()

}