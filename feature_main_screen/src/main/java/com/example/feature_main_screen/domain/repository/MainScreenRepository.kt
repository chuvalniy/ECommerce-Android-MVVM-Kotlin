package com.example.feature_main_screen.domain.repository

import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {

    fun fetchMainScreenDataSource(): Flow<Resource<DomainDataSource>>
}