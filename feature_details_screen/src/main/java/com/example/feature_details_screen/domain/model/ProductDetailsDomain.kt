package com.example.feature_details_screen.domain.model

import com.example.core.ui.DisplayableItem

data class ProductDetailsDomain(
    val cpu: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val id: Int,
    val images: List<String>,
    val isFavorites: Boolean,
    val price: String,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String
) : DisplayableItem {
    override val itemId: Int
        get() = id
}
