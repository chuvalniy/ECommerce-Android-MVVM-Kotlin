package com.example.feature_search.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_search.domain.model.DomainDataSource
import com.example.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchDataUseCase(
    private val repository: SearchRepository
) {

    operator fun invoke(
        query: String,
        brand: String,
        leftPrice: Int,
        rightPrice: Int,
        category: String
    ): Flow<Resource<List<DomainDataSource>>> {
        if (brand == "All products") return repository.searchData(
            query,
            "",
            leftPrice,
            rightPrice,
            category
        )

        return repository.searchData(query, brand, leftPrice, rightPrice, category)
    }
}