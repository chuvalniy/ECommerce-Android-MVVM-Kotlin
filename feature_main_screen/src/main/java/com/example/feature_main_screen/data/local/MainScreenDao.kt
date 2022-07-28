package com.example.feature_main_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_main_screen.data.local.model.CacheDataSource

@Dao
interface MainScreenDao {

    @Query("SELECT * FROM ${MainScreenDatabase.DATABASE_NAME}")
    suspend fun fetchCache(): CacheDataSource?

    @Query("DELETE FROM ${MainScreenDatabase.DATABASE_NAME}")
    suspend fun clearCache()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: CacheDataSource)
}