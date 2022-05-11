package com.example.feature_cart.data.remote

import com.example.feature_cart.data.remote.dto.CartDto
import retrofit2.http.GET

interface CartScreenApi {

    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun fetchCartInfo(): CartDto

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }
}