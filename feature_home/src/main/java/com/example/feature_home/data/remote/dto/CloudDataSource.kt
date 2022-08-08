package com.example.feature_home.data.remote.dto

class CloudDataSource (
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imagePreview: String = "",
    val images: List<String> = emptyList(),
    val price: Int = 0,
    val subcategories: List<String> = emptyList(),
    val category: Int = 0,
)