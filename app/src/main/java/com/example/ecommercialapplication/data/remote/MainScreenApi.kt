package com.example.ecommercialapplication.data.remote

import com.example.ecommercialapplication.data.remote.dto.CloudDataSource
import retrofit2.http.GET

interface MainScreenApi {

    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun fetchCloudDataSource(): CloudDataSource
}