package com.example.ecommercialapplication.data.remote.dto

data class CloudDataSource(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)
