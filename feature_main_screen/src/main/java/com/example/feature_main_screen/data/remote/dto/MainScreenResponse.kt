package com.example.feature_main_screen.data.remote.dto

data class MainScreenResponse(
    val best_seller: List<BestSellerDto>,
    val home_store: List<HomeStoreDto>
)