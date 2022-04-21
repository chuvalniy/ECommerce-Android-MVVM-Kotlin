package com.example.ecommercialapplication.feature_main.domain.repository

import com.example.ecommercialapplication.core.utils.Resource
import com.example.ecommercialapplication.feature_main.domain.model.ShopItem

interface ShopRepository {

    suspend fun fetchShopItems(): Resource<ShopItem>
}