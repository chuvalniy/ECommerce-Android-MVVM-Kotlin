package com.example.feature_home.domain.model

data class DomainDataSource(
    val id: String,
    val title: String,
    val description: String,
    val imagePreview: String,
    val image: String,
    val price: String,
    val subcategories: String,
    val category: Int,
)