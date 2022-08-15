package com.example.feature_search.domain.repository

import com.example.core.utils.Resource
import com.example.feature_search.domain.model.DomainDataSource
import com.example.feature_search.presentation.view_model.PriceFilter
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun searchData(
        query: String,
        brand: String,
        price: PriceFilter
    ): Flow<Resource<List<DomainDataSource>>>
}
