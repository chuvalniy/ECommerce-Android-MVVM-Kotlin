package com.example.feature_search.presentation.view_model

enum class PriceFilter(val priceLeft: Int, val priceRight: Int) {
    ALL(priceLeft = 0, priceRight = Int.MAX_VALUE),
    LOWEST(priceLeft = 0, priceRight = 399),
    AVERAGE(priceLeft = 400, priceRight = 999),
    HIGHEST(priceLeft = 1000, priceRight = Int.MAX_VALUE)
}