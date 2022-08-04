package com.example.feature_home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_home.data.local.model.CacheDataSource
import com.example.feature_home.data.local.utils.Converters

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HomeScreenDatabase : RoomDatabase() {
    abstract val dao: HomeScreenDao

    companion object {
        const val DATABASE_NAME = "home_screen_cache"
    }
}