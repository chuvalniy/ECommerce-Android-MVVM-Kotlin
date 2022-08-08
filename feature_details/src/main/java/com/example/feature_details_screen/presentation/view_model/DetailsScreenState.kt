package com.example.feature_details_screen.presentation.view_model

import com.example.feature_details_screen.domain.model.DomainDataSource

data class DetailsScreenState(
    val data: DomainDataSource = DomainDataSource(
        id = "",
        images = emptyList(),
        price = "",
        title = "",
        description = "",
        rating = 0F
    ),
    val isLoading: Boolean = false,
)