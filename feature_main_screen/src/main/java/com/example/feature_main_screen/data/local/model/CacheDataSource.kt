package com.example.feature_main_screen.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.local.MainScreenDatabase

@Entity(tableName = MainScreenDatabase.DATABASE_NAME)
data class CacheDataSource(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bestSellers: List<BestSellerEntity>,
    val hotSales: List<HotSalesEntity>,
)