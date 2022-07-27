package com.example.feature_details_screen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_details_screen.data.local.DetailsScreenDatabase

@Entity(tableName = DetailsScreenDatabase.DATABASE_NAME)
data class ProductDetailsEntity(
    @PrimaryKey
    val id: String,
    val cpu: String,
    val camera: String,
    val capacity: List<CapacityEntity>,
    val color: List<ColorEntity>,
    val images: List<ImageEntity>,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Double,
    val sd: String,
    val ssd: String,
    val title: String
)