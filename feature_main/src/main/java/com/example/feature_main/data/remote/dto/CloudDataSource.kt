package com.example.feature_main.data.remote.dto

data class CloudDataSource(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)
