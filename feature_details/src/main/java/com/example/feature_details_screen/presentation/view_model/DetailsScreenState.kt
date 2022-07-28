package com.example.feature_details_screen.presentation.view_model

import com.example.feature_details_screen.domain.model.ProductDetailsDomain

data class DetailsScreenState(
    val data: ProductDetailsDomain = ProductDetailsDomain(
        cpu = "",
        camera = "",
        capacity = emptyList(),
        color = emptyList(),
        id = 0,
        images = emptyList(),
        isFavorites = false,
        price = "",
        rating = 0F,
        sd = "",
        ssd = "",
        title = ""
    ),
    val isLoading: Boolean = false
)