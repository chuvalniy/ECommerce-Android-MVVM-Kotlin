package com.example.feature_main_screen.domain.repository

import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.model.DomainDataSource

interface MainScreenRepository {

    suspend fun fetchMainScreenItems(): Resource<DomainDataSource>
}