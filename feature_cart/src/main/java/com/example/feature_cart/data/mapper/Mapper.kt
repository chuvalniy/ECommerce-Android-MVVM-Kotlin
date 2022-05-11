package com.example.feature_cart.data.mapper

import com.example.feature_cart.data.remote.dto.BasketDto
import com.example.feature_cart.data.remote.dto.CartDto
import com.example.feature_cart.domain.model.BasketDomain
import com.example.feature_cart.domain.model.CartDomain

fun CartDto.toCartDomain(): CartDomain {
    return CartDomain(
        basket = basket.map { it.toBasketDomain() },
        delivery = delivery,
        id = id,
        total = total
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