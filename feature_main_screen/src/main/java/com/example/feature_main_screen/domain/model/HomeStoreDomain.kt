package com.example.feature_main_screen.domain.model

data class HomeStoreDomain(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)
