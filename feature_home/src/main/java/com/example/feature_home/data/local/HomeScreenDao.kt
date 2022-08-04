package com.example.feature_home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_home.data.local.model.CacheDataSource

@Dao
interface HomeScreenDao {

    @Query("SELECT * FROM ${HomeScreenDatabase.DATABASE_NAME}")
    suspend fun fetchCache(): CacheDataSource?

    @Query("DELETE FROM ${HomeScreenDatabase.DATABASE_NAME}")
    suspend fun clearCache()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: CacheDataSource)
}