package com.example.feature_search.presentation.view_model

import com.example.feature_search.domain.model.DomainDataSource

data class SearchState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val brand: String = "",
    val priceFilter: PriceFilter = PriceFilter.ALL
)