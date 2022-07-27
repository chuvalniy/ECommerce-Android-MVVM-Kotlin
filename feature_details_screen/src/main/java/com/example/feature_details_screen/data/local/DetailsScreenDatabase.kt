package com.example.feature_details_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.feature_details_screen.data.local.entity.ProductDetailsEntity
import com.example.feature_details_screen.data.local.utils.Converters

@Database(entities = [ProductDetailsEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class DetailsScreenDatabase : RoomDatabase() {
    abstract val dao: DetailsScreenDao

    companion object {
        const val DATABASE_NAME = "product_details_cache"
    }
}