package com.example.feature_main_screen.domain.model

data class BestSeller(
    val discount_price: String,
    val id: String,
    val is_favorites: Boolean,
    val picture: String,
    val price_without_discount: String,
    val title: String
)
