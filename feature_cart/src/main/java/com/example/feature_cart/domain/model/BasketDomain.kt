package com.example.feature_cart.domain.model

import com.example.core.ui.DisplayableItem

class BasketDomain(
    val id: Int,
    val images: String,
    val price: Int,
    val title: String
) : DisplayableItem {
    override val itemId: Int = id
}