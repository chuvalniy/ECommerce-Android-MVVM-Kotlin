package com.example.feature_cart.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_cart.data.local.CartDatabase

@Entity(tableName = CartDatabase.DATABASE_NAME)
data class CartEntity(
    @PrimaryKey
    val id: String,
    val basket: List<BasketEntity>,
    val delivery: String,
    val total: Int
)