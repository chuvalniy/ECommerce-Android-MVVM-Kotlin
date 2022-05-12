package com.example.feature_main_screen.data.remote

import com.example.feature_main_screen.data.remote.dto.CartDto
import com.example.feature_main_screen.data.remote.dto.MainScreenResponse
import retrofit2.http.GET

interface MainScreenApi {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun fetchMainScreenItemsFromApi() : MainScreenResponse

    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun fetchCartInfoFromApi() : CartDto

}