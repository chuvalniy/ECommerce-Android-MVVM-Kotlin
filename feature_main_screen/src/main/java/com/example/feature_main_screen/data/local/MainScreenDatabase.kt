package com.example.feature_main_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.feature_main_screen.data.local.model.CacheDataSource
import com.example.feature_main_screen.data.local.utils.Converters

@Database(
    entities = [CacheDataSource::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MainScreenDatabase : RoomDatabase() {
    abstract val dao: MainScreenDao

    companion object {
        const val DATABASE_NAME = "main_screen_cache"
    }
}