package com.example.feature_details_screen.domain.repository

import com.example.core.utils.Resource
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import kotlinx.coroutines.flow.Flow

interface DetailsScreenRepository {

    fun fetchProductDetails(): Flow<Resource<ProductDetailsDomain>>
}