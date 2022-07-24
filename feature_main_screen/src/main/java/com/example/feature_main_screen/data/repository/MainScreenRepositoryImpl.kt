package com.example.feature_main_screen.data.repository

import com.example.core.utils.Resource
import com.example.feature_main_screen.data.mapper.toBestSeller
import com.example.feature_main_screen.data.mapper.toHotSales
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.domain.model.DomainDataSource
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import retrofit2.HttpException
import java.io.IOException

class MainScreenRepositoryImpl(
    private val api: MainScreenApi
) : MainScreenRepository {

    override suspend fun fetchMainScreenDataSource(): Resource<DomainDataSource> {
        return try {
            val mainScreenResponse = api.fetchMainScreenItemsFromApi()

            val domainHomeStore = mainScreenResponse.hotSales.map { it.toHotSales() }
            val domainBestSeller = mainScreenResponse.bestSellers.map { it.toBestSeller() }

            val cartInfo = api.fetchCartInfoFromApi().basket.size

            val response = DomainDataSource(
                bestSellers = domainBestSeller,
                hotSales = domainHomeStore,
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