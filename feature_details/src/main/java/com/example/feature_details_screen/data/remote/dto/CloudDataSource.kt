package com.example.feature_details_screen.data.remote.dto

data class CloudDataSource(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val images: List<String> = emptyList(),
    val price: Int = 0,
    val rating: Float = 0F,
)