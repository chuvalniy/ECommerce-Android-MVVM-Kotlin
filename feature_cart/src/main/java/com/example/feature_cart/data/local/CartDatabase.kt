package com.example.feature_cart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_cart.data.local.entity.CartEntity
import com.example.feature_cart.data.local.utils.Converters

@Database(entities = [CartEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CartDatabase : RoomDatabase() {

    abstract val dao: CartDao

    companion object {
        const val DATABASE_NAME = "cart_cache"
    }
}