package com.example.feature_details_screen.presentation.view_model

import com.example.feature_details_screen.domain.model.ProductDetailsDomain

data class DetailsScreenState(
    val data: ProductDetailsDomain? = null,
    val isLoading: Boolean = false
)