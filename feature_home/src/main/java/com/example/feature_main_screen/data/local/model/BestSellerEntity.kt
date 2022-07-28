package com.example.feature_main_screen.data.local.model

data class BestSellerEntity(
    val discountPrice: Int,
    val id: Int,
    val isFavorites: Boolean,
    val picture: String,
    val priceWithoutDiscount: Int,
    val title: String
)