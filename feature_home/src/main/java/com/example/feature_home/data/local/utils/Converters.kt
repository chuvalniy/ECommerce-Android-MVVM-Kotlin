package com.example.feature_home.data.local.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.utils.parser.JsonParser
import com.example.feature_home.data.local.model.BestSellerEntity
import com.example.feature_home.data.local.model.HotSalesEntity
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromBestSeller(json: String): List<BestSellerEntity> {
        return jsonParser.fromJson<ArrayList<BestSellerEntity>>(
            json,
            object : TypeToken<ArrayList<BestSellerEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toBestSeller(actors: List<BestSellerEntity>): String {
        return jsonParser.toJson(
            actors,
            object : TypeToken<ArrayList<BestSellerEntity>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromHotSales(json: String): List<HotSalesEntity> {
        return jsonParser.fromJson<ArrayList<HotSalesEntity>>(
            json,
            object : TypeToken<ArrayList<HotSalesEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toHotSales(actors: List<HotSalesEntity>): String {
        return jsonParser.toJson(
            actors,
            object : TypeToken<ArrayList<HotSalesEntity>>() {}.type
        ) ?: "[]"
    }

}