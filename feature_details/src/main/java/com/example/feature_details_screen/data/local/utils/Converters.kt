package com.example.feature_details_screen.data.local.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.utils.parser.JsonParser
import com.example.feature_details_screen.data.local.entity.ImageEntity
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

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