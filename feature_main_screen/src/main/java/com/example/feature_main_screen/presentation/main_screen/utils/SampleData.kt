package com.example.feature_main_screen.presentation.main_screen.utils

import com.example.feature_main_screen.R

object SampleData {

    val categories = mutableMapOf(
        "Phones" to R.drawable.ic_phone,
        "Computer" to R.drawable.ic_computer,
        "Health" to R.drawable.ic_health,
        "Books" to R.drawable.ic_books,
        "Other" to R.drawable.ic_devices_other
    )

    val brandFilter = mutableListOf(
        "Samsung",
        "Xiaomi"
    )

    val priceFilter = mutableListOf(
        "$0 - $10.000",
        "$0 - $300",
        "$300 - $500",
        "$500 - $1000",
        "$1000 - $10.000"
    )

    val sizeFilter = mutableListOf(
        "4.5 to 5.5 inches",
        "5.5 to 6.0 inches"
    )
}