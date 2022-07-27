package com.example.feature_cart.data.mapper

import com.example.feature_cart.data.local.entity.BasketEntity
import com.example.feature_cart.data.local.entity.CartEntity
import com.example.feature_cart.data.remote.dto.BasketDto
import com.example.feature_cart.data.remote.dto.CartDto
import com.example.feature_cart.domain.model.BasketDomain
import com.example.feature_cart.domain.model.CartDomain

fun CartDto.toCartEntity(): CartEntity {
    return CartEntity(
        basket = basket.map { it.toBasketEntity() },
        delivery = delivery,
        id = id,
        total = total
    )
}


fun BasketDto.toBasketEntity(): BasketEntity {
    return BasketEntity(
        id = id,
        images = images,
        price = price,
        title = title
    )
}

fun CartEntity.toCartDomain(): CartDomain {
    return CartDomain(
        basket = basket.map { it.toBasketDomain() },
        delivery = delivery,
        id = id,
        total = total.toString()
    )
}

fun BasketEntity.toBasketDomain(): BasketDomain {
    return BasketDomain(
        id = id,
        images = images,
        price = price,
        title = title
    )
}