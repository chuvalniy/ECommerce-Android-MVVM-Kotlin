package com.example.feature_search.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_search.domain.model.DomainDataSource
import com.example.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchDataUseCase(
    private val repository: SearchRepository
) {

    operator fun invoke(): Flow<Resource<List<DomainDataSource>>> {
//        if (query.isBlank()) return flow { }

        return repository.searchData()
    }
}