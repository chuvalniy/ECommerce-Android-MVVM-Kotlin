package com.example.feature_search.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_search.data.local.SearchDatabase

@Entity(tableName = SearchDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val title: String,
    val image: String,
    val price: Int,
    val subcategories: String,
    val category: Int,
    val brand: String
)