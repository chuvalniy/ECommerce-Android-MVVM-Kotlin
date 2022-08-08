package com.example.feature_details_screen.domain.repository

import com.example.core.utils.Resource
import com.example.feature_details_screen.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun fetchData(id: String): Flow<Resource<DomainDataSource>>
}