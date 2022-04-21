package com.example.ecommercialapplication.feature_main.data.repository

import com.example.ecommercialapplication.core.utils.Resource
import com.example.ecommercialapplication.feature_main.data.mapper.toShopItem
import com.example.ecommercialapplication.feature_main.data.remote.ShopApi
import com.example.ecommercialapplication.feature_main.domain.model.ShopItem
import com.example.ecommercialapplication.feature_main.domain.repository.ShopRepository
import retrofit2.HttpException
import java.io.IOException

class ShopRepositoryImpl(
    private val api: ShopApi
) : ShopRepository {
    override suspend fun fetchShopItems(): Resource<ShopItem> {
        return try {
            val data = api.fetchShopItemsFromApi().toShopItem()
            Resource.Success(data)
        } catch (e: IOException) {
            Resource.Error(error = e.message)
        } catch (e: HttpException) {
            Resource.Error(error = e.message)
        }
    }
}