package com.example.feature_search.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_search.domain.model.DomainDataSource
import com.example.feature_search.domain.repository.SearchRepository
import com.example.feature_search.presentation.view_model.PriceFilter
import kotlinx.coroutines.flow.Flow

class SearchDataUseCase(
    private val repository: SearchRepository
) {

    operator fun invoke(
        query: String,
        brand: String,
        price: PriceFilter
    ): Flow<Resource<List<DomainDataSource>>> {

        return repository.searchData(query, brand, price)
    }
}