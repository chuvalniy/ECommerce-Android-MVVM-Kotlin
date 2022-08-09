package com.example.feature_search.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_search.domain.model.DomainDataSource
import com.example.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchDataUseCase(
    private val repository: SearchRepository
) {

    operator fun invoke(query: String): Flow<Resource<List<DomainDataSource>>> {
        if (query.isBlank()) return flow { }

        return repository.searchData(query)
    }
}