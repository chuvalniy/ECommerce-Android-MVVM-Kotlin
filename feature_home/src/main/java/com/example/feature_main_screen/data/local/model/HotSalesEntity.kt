package com.example.feature_main_screen.data.local.model

data class HotSalesEntity(
    val id: Int,
    val isBuy: Boolean,
    val isNew: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)