package com.example.ecommercialapplication.feature_main.data.remote.dto

data class HomeStoreDto(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)