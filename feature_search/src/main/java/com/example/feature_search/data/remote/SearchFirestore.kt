package com.example.feature_search.data.remote

import com.example.feature_search.data.remote.dto.CloudDataSource

interface SearchFirestore {

    suspend fun fetchData(): List<CloudDataSource>
}