package com.example.feature_main_screen.data.mapper

import com.example.feature_main_screen.data.remote.dto.BasketDto
import com.example.feature_main_screen.data.remote.dto.BestSellerDto
import com.example.feature_main_screen.data.remote.dto.HomeStoreDto
import com.example.feature_main_screen.data.remote.dto.MainScreenResponse
import com.example.feature_main_screen.domain.model.BasketDomain
import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HomeStoreDomain
import com.example.feature_main_screen.domain.model.MainScreenDomain

fun MainScreenResponse.toMainScreenDomain(): MainScreenDomain {
    return MainScreenDomain(
        best_seller = best_seller.map { it.toBestSeller() },
        home_store = home_store.map { it.toHomeStore() }
    )
}

fun BestSellerDto.toBestSeller(): BestSellerDomain {
    return BestSellerDomain(
        discount_price = discount_price.toString(),
        id = id,
        picture = picture,
        is_favorites = is_favorites,
        price_without_discount = price_without_discount.toString(),
        title = title
    )
}

fun HomeStoreDto.toHomeStore(): HomeStoreDomain {
    return HomeStoreDomain(
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
        id = id.toString(),
        images = images,
        price = price.toString(),
        title = title
    )
}