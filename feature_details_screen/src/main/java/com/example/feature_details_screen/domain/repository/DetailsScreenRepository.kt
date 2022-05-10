package com.example.feature_details_screen.domain.repository

import com.example.core.utils.Resource
import com.example.feature_details_screen.domain.model.ProductDetailsDomain

interface DetailsScreenRepository {

    suspend fun fetchProductDetails(): Resource<ProductDetailsDomain>
}