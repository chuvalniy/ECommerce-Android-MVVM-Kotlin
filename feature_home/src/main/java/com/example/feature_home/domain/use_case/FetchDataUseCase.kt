package com.example.feature_home.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow

class FetchDataUseCase(
    private val repository: HomeScreenRepository
) {

    fun execute(selectedCategory: Int): Flow<Resource<List<DomainDataSource>>> {
        return repository.fetchData(selectedCategory)
    }

}