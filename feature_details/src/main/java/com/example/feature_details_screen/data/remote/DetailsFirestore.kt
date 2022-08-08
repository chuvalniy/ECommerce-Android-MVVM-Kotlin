package com.example.feature_details_screen.data.remote

import com.example.feature_details_screen.data.remote.dto.CloudDataSource

interface DetailsFirestore {

    suspend fun fetchCloudDataSource(id: String): CloudDataSource?
}