package com.example.feature_main_screen.data.repository

import com.example.core.utils.Resource
import com.example.feature_main_screen.data.mapper.toBasketDomain
import com.example.feature_main_screen.data.mapper.toMainScreenDomain
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.domain.model.BasketDomain
import com.example.feature_main_screen.domain.model.MainScreenDomain
import com.example.feature_main_screen.domain.repository.MainScreenRepository
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

    override suspend fun fetchCartInfo(): Resource<List<BasketDomain>> {
        return try {
            val data = api.fetchCartInfoFromApi()
            Resource.Success(data.basket.map { it.toBasketDomain() })
        } catch (e: HttpException) {
            Resource.Error(error = e.message)
        } catch (e: IOException) {
            Resource.Error(error = e.message)
        }
    }

}