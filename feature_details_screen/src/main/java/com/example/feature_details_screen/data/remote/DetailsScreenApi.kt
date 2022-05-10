package com.example.feature_details_screen.data.remote

import com.example.feature_details_screen.data.remote.dto.ProductDetailsDto
import retrofit2.http.GET

interface DetailsScreenApi {

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun fetchProductDetails(): ProductDetailsDto

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }
}