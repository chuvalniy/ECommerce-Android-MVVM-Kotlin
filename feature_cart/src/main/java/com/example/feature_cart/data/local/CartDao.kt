package com.example.feature_cart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_cart.data.local.entity.CartEntity

@Dao
interface CartDao {

    @Query("SELECT * FROM ${CartDatabase.DATABASE_NAME}")
    suspend fun fetchFromCache(): CartEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoCache(item: CartEntity)

    @Query("DELETE FROM ${CartDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}