package com.example.feature_details_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_details_screen.data.local.entity.ProductDetailsEntity

@Dao
interface DetailsScreenDao {

    @Query("SELECT * FROM ${DetailsScreenDatabase.DATABASE_NAME}")
    suspend fun fetchCache(): ProductDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(productDetailsEntity: ProductDetailsEntity)

    @Query("DELETE FROM ${DetailsScreenDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}