package com.example.feature_cart.domain.model

data class CartDomain(
    val basket: List<BasketDomain>,
    val delivery: String,
    val id: String,
    val total: String
)
