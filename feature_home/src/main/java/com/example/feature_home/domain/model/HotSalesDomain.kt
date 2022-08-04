package com.example.feature_home.domain.model

data class HotSalesDomain(
    val id: Int,
    val isBuy: Boolean,
    val isNew: Boolean?,
    val picture: String,
    val subtitle: String,
    val title: String
)
