package com.example.feature_home.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_home.data.local.HomeScreenDatabase

@Entity(tableName = HomeScreenDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bestSellers: List<BestSellerEntity>,
    val hotSales: List<HotSalesEntity>,
)