package com.example.ecommercialapplication.feature_main.domain.model

import com.example.ecommercialapplication.feature_main.data.remote.dto.BestSellerDto
import com.example.ecommercialapplication.feature_main.data.remote.dto.HomeStoreDto

data class ShopItem(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)