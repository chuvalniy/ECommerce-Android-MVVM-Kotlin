package com.example.feature_search.domain.repository

import com.example.core.utils.Resource
import com.example.feature_search.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun searchData(): Flow<Resource<List<DomainDataSource>>>
}