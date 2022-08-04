package com.example.feature_home.data.local.model

data class HotSalesEntity(
    val id: Int,
    val isBuy: Boolean,
    val isNew: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)