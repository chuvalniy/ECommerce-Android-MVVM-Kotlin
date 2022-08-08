package com.example.feature_home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_home.data.local.model.CacheDataSource

@Database(
    entities = [CacheDataSource::class],
    version = 1,
)
abstract class HomeScreenDatabase : RoomDatabase() {
    abstract val dao: HomeScreenDao

    companion object {
        const val DATABASE_NAME = "home_screen_cache"
    }
}