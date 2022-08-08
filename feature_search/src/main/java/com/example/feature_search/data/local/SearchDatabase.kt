package com.example.feature_search.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_search.data.local.entity.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
abstract class SearchDatabase : RoomDatabase() {
    abstract val dao: SearchDao

    companion object {
        const val DATABASE_NAME = "search_cache"
    }
}