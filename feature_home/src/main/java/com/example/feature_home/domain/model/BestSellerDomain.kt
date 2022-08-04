package com.example.feature_home.domain.model

data class BestSellerDomain(
    val discountPrice: String,
    val id: Int,
    val isFavorites: Boolean,
    val picture: String,
    val priceWithoutDiscount: String,
    val title: String
)
