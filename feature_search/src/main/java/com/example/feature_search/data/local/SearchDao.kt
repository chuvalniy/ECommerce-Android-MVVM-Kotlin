package com.example.feature_search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_search.data.local.entity.CacheDataSource
import com.example.feature_search.presentation.view_model.PriceFilter

@Dao
interface SearchDao {


    @Query("SELECT * FROM ${SearchDatabase.DATABASE_NAME}" +
            " WHERE title LIKE '%' || :query || '%'" +
            " AND brand LIKE '%' || :brand || '%'" +
            " AND price BETWEEN :priceLeftBound AND :priceRightBound"
    )
    suspend fun fetchCache(
        query: String,
        brand: String,
        priceLeftBound: Int,
        priceRightBound: Int
    ): List<CacheDataSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: List<CacheDataSource>)

    @Query("DELETE FROM ${SearchDatabase.DATABASE_NAME} WHERE title IN(:titles)")
    suspend fun clearCache(titles: List<String>)
}