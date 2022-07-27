package com.example.feature_details_screen.data.local.utils

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.utils.parser.JsonParser
import com.example.feature_details_screen.data.local.entity.CapacityEntity
import com.example.feature_details_screen.data.local.entity.ColorEntity
import com.example.feature_details_screen.data.local.entity.ImageEntity
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromCapacity(json: String): List<CapacityEntity> {
        return jsonParser.fromJson<ArrayList<CapacityEntity>>(
            json,
            object : TypeToken<ArrayList<CapacityEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toCapacity(actors: List<CapacityEntity>): String {
        return jsonParser.toJson(
            actors,
            object : TypeToken<ArrayList<CapacityEntity>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromColor(json: String): List<ColorEntity> {
        return jsonParser.fromJson<ArrayList<ColorEntity>>(
            json,
            object : TypeToken<ArrayList<ColorEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toColor(actors: List<ColorEntity>): String {
        return jsonParser.toJson(
            actors,
            object : TypeToken<ArrayList<ColorEntity>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromImage(json: String): List<ImageEntity> {
        return jsonParser.fromJson<ArrayList<ImageEntity>>(
            json,
            object : TypeToken<ArrayList<ImageEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toImage(actors: List<ImageEntity>): String {
        return jsonParser.toJson(
            actors,
            object : TypeToken<ArrayList<ImageEntity>>() {}.type
        ) ?: "[]"
    }
}