package com.example.feature_home.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_home.data.local.HomeScreenDatabase

@Entity(tableName = HomeScreenDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val imagePreview: String,
    val image: String,
    val price: Int,
    val subcategories: String,
    val category: Int,
)