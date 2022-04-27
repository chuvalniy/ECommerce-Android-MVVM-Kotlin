package com.example.ecommercialapplication.feature_main.data.repository

import com.example.ecommercialapplication.core.utils.Resource
import com.example.ecommercialapplication.feature_main.data.mapper.toMainScreenDomain
import com.example.ecommercialapplication.feature_main.data.remote.MainScreenApi
import com.example.ecommercialapplication.feature_main.domain.model.MainScreenDomain
import com.example.ecommercialapplication.feature_main.domain.repository.MainScreenRepository
import retrofit2.HttpException
import java.io.IOException

class MainScreenRepositoryImpl(
    private val api: MainScreenApi
) : MainScreenRepository {
    override suspend fun fetchMainScreenItems(): Resource<MainScreenDomain> {
        return try {
            val data = api.fetchMainScreenItemsFromApi().toMainScreenDomain()
            Resource.Success(data)
        } catch (e: IOException) {
            Resource.Error(error = e.message)
        } catch (e: HttpException) {
            Resource.Error(error = e.message)
        }
    }
}