package com.example.feature_home.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BestSellerDto(
    @SerializedName("discount_price")
    val discountPrice: Int,
    val id: Int,
    @SerializedName("is_favorites")
    val isFavorites: Boolean,
    val picture: String,
    @SerializedName("price_without_discount")
    val priceWithoutDiscount: Int,
    val title: String
)