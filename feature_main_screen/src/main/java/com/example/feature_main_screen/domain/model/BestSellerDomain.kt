package com.example.feature_main_screen.domain.model

import com.example.core.ui.DisplayableItem

data class BestSellerDomain(
    val discount_price: String,
    val id: Int,
    val is_favorites: Boolean,
    val picture: String,
    val price_without_discount: String,
    val title: String
) : DisplayableItem {
    override val itemId: Int = id
}
