package com.example.feature_details_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_details_screen.data.local.entity.CacheDataSource
import com.example.feature_details_screen.data.local.utils.Converters

@Database(entities = [CacheDataSource::class], version = 1)
@TypeConverters(Converters::class)
abstract class DetailsDatabase : RoomDatabase() {
    abstract val dao: DetailsDao

    companion object {
        const val DATABASE_NAME = "product_details_cache"
    }
}