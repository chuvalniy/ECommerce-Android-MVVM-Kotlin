package com.example.feature_cart.data.local.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.utils.parser.JsonParser
import com.example.feature_cart.data.local.entity.BasketEntity
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromBasketJson(json: String): List<BasketEntity> {
        return jsonParser.fromJson<ArrayList<BasketEntity>>(
            json,
            object : TypeToken<ArrayList<BasketEntity>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toBasketJson(basket: List<BasketEntity>): String {
        return jsonParser.toJson(
            basket,
            object : TypeToken<ArrayList<BasketEntity>>() {}.type
        ) ?: "[]"
    }
}