package com.example.feature_main_screen.data.remote

import com.example.feature_main_screen.data.remote.dto.CloudDataSource
import retrofit2.http.GET

interface HomeScreenApi {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun fetchCloudDataSource(): CloudDataSource
}