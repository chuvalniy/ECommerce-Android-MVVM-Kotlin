package com.example.feature_main_screen.data.mapper

import com.example.feature_main_screen.data.remote.dto.BasketDto
import com.example.feature_main_screen.data.remote.dto.BestSellerDto
import com.example.feature_main_screen.data.remote.dto.HomeStoreDto
import com.example.feature_main_screen.data.remote.dto.MainScreenResponse
import com.example.feature_main_screen.domain.model.BasketDomain
import com.example.feature_main_screen.domain.model.BestSeller
import com.example.feature_main_screen.domain.model.HomeStore
import com.example.feature_main_screen.domain.model.MainScreenDomain

fun MainScreenResponse.toMainScreenDomain(): MainScreenDomain {
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

fun BasketDto.toBasketDomain(): BasketDomain {
    return BasketDomain(
        id = id,
        images = images,
        price = price,
        title = title
    )
}