package com.example.feature_main_screen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MainScreenResponse(
    @SerializedName("best_seller")
    val bestSellers: List<BestSellerDto>,
    @SerializedName("home_store")
    val hotSales: List<HotSalesDto>
)