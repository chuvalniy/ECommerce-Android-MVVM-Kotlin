package com.example.feature_main_screen.data.mapper

import com.example.feature_main_screen.data.remote.dto.BestSellerDto
import com.example.feature_main_screen.data.remote.dto.HotSalesDto
import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HotSalesDomain


fun BestSellerDto.toBestSeller(): BestSellerDomain {
    return BestSellerDomain(
        discountPrice = discountPrice.toString(),
        id = id,
        picture = picture,
        isFavorites = isFavorites,
        priceWithoutDiscount = priceWithoutDiscount.toString(),
        title = title
    )
}

fun HotSalesDto.toHotSales(): HotSalesDomain {
    return HotSalesDomain(
        id = id,
        isBuy = isBuy,
        isNew = isNew,
        picture = picture,
        subtitle = subtitle,
        title = title
    )
}
