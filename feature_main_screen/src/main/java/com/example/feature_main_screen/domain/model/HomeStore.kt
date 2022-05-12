package com.example.feature_main_screen.domain.model

data class HomeStore(
    val id: String,
    val is_buy: Boolean,
    val is_new: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)
