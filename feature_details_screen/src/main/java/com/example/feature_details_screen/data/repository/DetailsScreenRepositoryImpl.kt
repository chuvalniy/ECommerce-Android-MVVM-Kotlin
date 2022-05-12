package com.example.feature_details_screen.data.repository

import com.example.core.utils.Resource
import com.example.feature_details_screen.data.mapper.toProductDetailsDomain
import com.example.feature_details_screen.data.remote.DetailsScreenApi
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.domain.repository.DetailsScreenRepository
import java.lang.Exception

class DetailsScreenRepositoryImpl(
    private val api: DetailsScreenApi
): DetailsScreenRepository {

    override suspend fun fetchProductDetails(): Resource<ProductDetailsDomain> {
        return try {
            val remoteData = api.fetchProductDetails().toProductDetailsDomain()
            Resource.Success(remoteData)
        } catch (e: Exception) {
         Resource.Error(error = e.message)
        } catch (e: Exception) {
            Resource.Error(error = e.message)
        }
    }
}