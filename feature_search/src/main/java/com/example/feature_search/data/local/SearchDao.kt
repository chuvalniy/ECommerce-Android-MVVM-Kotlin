package com.example.feature_search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_search.data.local.entity.CacheDataSource

@Dao
interface SearchDao {

    @Query( " SELECT * FROM ${SearchDatabase.DATABASE_NAME}" +
            " WHERE title LIKE '%' || :query || '%'" +
            " AND brand LIKE '%' || :brand || '%'" +
            " AND price >= :leftPrice" +
            " AND price <= :rightPrice" +
            " AND subcategories LIKE '%' || :category || '%'")
    suspend fun fetchCache(
        query: String,
        brand: String,
        leftPrice: Int,
        rightPrice: Int,
        category: String
    ): List<CacheDataSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: List<CacheDataSource>)

    @Query("DELETE FROM ${SearchDatabase.DATABASE_NAME} WHERE title IN(:titles)")
    suspend fun clearCache(titles: List<String>)
}