package com.example.feature_main_screen.data.repository

import com.example.core.utils.Resource
import com.example.feature_main_screen.data.mapper.toBestSeller
import com.example.feature_main_screen.data.mapper.toHomeStore
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.domain.model.DomainDataSource
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import retrofit2.HttpException
import java.io.IOException

class MainScreenRepositoryImpl(
    private val api: MainScreenApi
) : MainScreenRepository {

    override suspend fun fetchMainScreenItems(): Resource<DomainDataSource> {
        return try {
            val mainScreenResponse = api.fetchMainScreenItemsFromApi()

            val domainHomeStore = mainScreenResponse.home_store.map { it.toHomeStore() }
            val domainBestSeller = mainScreenResponse.best_seller.map { it.toBestSeller() }
            val cartInfo = api.fetchCartInfoFromApi().basket.size

            val response = DomainDataSource(
                bestSeller = domainBestSeller,
                homeStore = domainHomeStore,
                cartInfo = cartInfo
            )

            Resource.Success(response)
        } catch (e: IOException) {
            Resource.Error(error = e.message)
        } catch (e: HttpException) {
            Resource.Error(error = e.message)
        }
    }
}