package com.example.feature_search.data.remote.dto

data class CloudDataSource(
    val id: String = "",
    val title: String = "",
    val images: List<String> = emptyList(),
    val price: Int = 0,
    val subcategories: List<String> = emptyList(),
    val category: Int = 0,
)