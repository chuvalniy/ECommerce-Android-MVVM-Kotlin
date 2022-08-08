package com.example.feature_details_screen.domain.model

data class DomainDataSource(
    val id: String,
    val title: String,
    val description: String,
    val images: List<String>,
    val price: String,
    val rating: Float
)