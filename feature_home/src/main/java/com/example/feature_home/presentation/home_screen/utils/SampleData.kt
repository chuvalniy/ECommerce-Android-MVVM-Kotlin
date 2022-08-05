package com.example.feature_home.presentation.home_screen.utils

import com.example.feature_home.R

object SampleData {

    val categories = listOf(
        CategoryItem(
            "Phones", R.drawable.ic_phone
        ),
        CategoryItem(
            "Computer", R.drawable.ic_computer,
        ),
        CategoryItem(
            "Health", R.drawable.ic_health,
        ),
        CategoryItem(
            "Books", R.drawable.ic_books,
        ),
        CategoryItem(
            "Other", R.drawable.ic_devices_other
        ),

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