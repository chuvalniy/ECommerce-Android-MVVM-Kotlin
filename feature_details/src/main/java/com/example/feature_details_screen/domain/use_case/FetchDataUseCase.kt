package com.example.feature_details_screen.domain.use_case

import com.example.feature_details_screen.domain.repository.DetailsRepository

class FetchDataUseCase(
    private val repository: DetailsRepository
) {

    operator fun invoke(id: String) = repository.fetchData(id)
}