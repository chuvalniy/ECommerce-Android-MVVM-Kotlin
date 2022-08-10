package com.example.feature_search.presentation.view_model

import com.example.feature_search.domain.model.DomainDataSource

data class SearchState(
    val data: List<DomainDataSource> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
    val selectedBrand: String = "",
    val priceLeft: Int = 0,
    val priceRight: Int = 6000,
    val selectedCategory: CategoryType = CategoryType.ALL_PRODUCTS
)