package com.example.ecommercialapplication.feature_main.data.mapper

import com.example.ecommercialapplication.feature_main.data.remote.dto.BestSellerDto
import com.example.ecommercialapplication.feature_main.data.remote.dto.HomeStoreDto
import com.example.ecommercialapplication.feature_main.data.remote.dto.MainScreenDto
import com.example.ecommercialapplication.feature_main.domain.model.BestSeller
import com.example.ecommercialapplication.feature_main.domain.model.HomeStore
import com.example.ecommercialapplication.feature_main.domain.model.MainScreenDomain

fun MainScreenDto.toMainScreenDomain(): MainScreenDomain {
    return MainScreenDomain(
        best_seller = best_seller.map { it.toBestSeller() },
        home_store = home_store.map { it.toHomeStore() }
    )
}

fun BestSellerDto.toBestSeller(): BestSeller {
    return BestSeller(
        discount_price = discount_price,
        id = id,
        picture = picture,
        is_favorites = is_favorites,
        price_without_discount = price_without_discount,
        title = title
    )
}

fun HomeStoreDto.toHomeStore(): HomeStore {
    return HomeStore(
        id = id,
        is_buy = is_buy,
        is_new = is_new,
        picture = picture,
        subtitle = subtitle,
        title = title
    )
}