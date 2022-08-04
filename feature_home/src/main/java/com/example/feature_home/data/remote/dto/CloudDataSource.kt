package com.example.feature_home.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CloudDataSource(
    @SerializedName("best_seller")
    val bestSellers: List<BestSellerDto>,
    @SerializedName("home_store")
    val hotSales: List<HotSalesDto>
)