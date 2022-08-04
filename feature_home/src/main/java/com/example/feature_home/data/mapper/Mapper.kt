package com.example.feature_home.data.mapper

import com.example.feature_home.data.local.model.BestSellerEntity
import com.example.feature_home.data.local.model.CacheDataSource
import com.example.feature_home.data.local.model.HotSalesEntity
import com.example.feature_home.data.remote.dto.BestSellerDto
import com.example.feature_home.data.remote.dto.CloudDataSource
import com.example.feature_home.data.remote.dto.HotSalesDto
import com.example.feature_home.domain.model.BestSellerDomain
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.model.HotSalesDomain

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        bestSellers = bestSellers.map { it.toBestSellerEntity() },
        hotSales = hotSales.map { it.toHotSalesEntity() }
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        bestSellers = bestSellers.map { it.toBestSellerDomain() },
        hotSales = hotSales.map { it.toHotSalesDomain() },
    )
}

fun BestSellerDto.toBestSellerEntity(): BestSellerEntity {
    return BestSellerEntity(
        discountPrice = discountPrice,
        id = id,
        picture = picture,
        isFavorites = isFavorites,
        priceWithoutDiscount = priceWithoutDiscount,
        title = title
    )
}

fun HotSalesDto.toHotSalesEntity(): HotSalesEntity {
    return HotSalesEntity(
        id = id,
        isBuy = isBuy,
        isNew = isNew,
        picture = picture,
        subtitle = subtitle,
        title = title
    )
}

fun BestSellerEntity.toBestSellerDomain(): BestSellerDomain {
    return BestSellerDomain(
        discountPrice = discountPrice.toString(),
        id = id,
        picture = picture,
        isFavorites = isFavorites,
        priceWithoutDiscount = priceWithoutDiscount.toString(),
        title = title
    )
}

fun HotSalesEntity.toHotSalesDomain(): HotSalesDomain {
    return HotSalesDomain(
        id = id,
        isBuy = isBuy,
        isNew = isNew,
        picture = picture,
        subtitle = subtitle,
        title = title
    )
}
