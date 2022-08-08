package com.example.feature_home.data.remote

import com.example.feature_home.data.remote.dto.CloudDataSource

interface HomeFirestore {

    suspend fun fetchCloudDataSource(category: Int): List<CloudDataSource>
}