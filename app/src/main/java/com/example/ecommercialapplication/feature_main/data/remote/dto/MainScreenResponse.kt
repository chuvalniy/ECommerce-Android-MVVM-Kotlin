package com.example.ecommercialapplication.feature_main.data.remote.dto

data class MainScreenResponse(
    val best_seller: List<BestSellerDto>,
    val home_store: List<HomeStoreDto>
)