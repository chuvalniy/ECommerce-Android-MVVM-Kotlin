package com.example.feature_details_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_details_screen.data.local.entity.CacheDataSource

@Dao
interface DetailsDao {

    @Query("SELECT * FROM ${DetailsDatabase.DATABASE_NAME} WHERE id = :id")
    suspend fun fetchCache(id: String): CacheDataSource?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cacheDataSource: CacheDataSource)

    @Query("DELETE FROM ${DetailsDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}