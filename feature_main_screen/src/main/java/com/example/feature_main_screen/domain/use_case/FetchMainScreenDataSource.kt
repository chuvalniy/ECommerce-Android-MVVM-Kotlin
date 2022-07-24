package com.example.feature_main_screen.domain.use_case

import com.example.feature_main_screen.domain.repository.MainScreenRepository

class FetchMainScreenDataSource(
    private val repository: MainScreenRepository
) {

    suspend  fun execute() = repository.fetchMainScreenDataSource()

}