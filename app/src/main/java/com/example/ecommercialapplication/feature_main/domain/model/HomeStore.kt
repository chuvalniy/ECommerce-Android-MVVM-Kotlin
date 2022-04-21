package com.example.ecommercialapplication.feature_main.domain.model

data class HomeStore(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)
