package com.example.feature_details_screen.presentation.view_model.model

import com.example.feature_details_screen.domain.model.ProductDetailsDomain

sealed class DetailScreenEvent {
    class Success(val data: ProductDetailsDomain) : DetailScreenEvent()
    class Failure(val error: String) : DetailScreenEvent()
    object Loading : DetailScreenEvent()
    object Empty : DetailScreenEvent()
}
