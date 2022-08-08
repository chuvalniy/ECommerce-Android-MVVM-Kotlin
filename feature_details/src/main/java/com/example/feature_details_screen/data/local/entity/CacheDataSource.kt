package com.example.feature_details_screen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_details_screen.data.local.DetailsDatabase

@Entity(tableName = DetailsDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val images: List<ImageEntity>,
    val price: Int,
    val rating: Float
)