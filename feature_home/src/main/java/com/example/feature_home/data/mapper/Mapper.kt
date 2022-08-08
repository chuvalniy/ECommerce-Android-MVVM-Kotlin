package com.example.feature_home.data.mapper

import com.example.feature_home.data.local.model.CacheDataSource
import com.example.feature_home.data.remote.dto.CloudDataSource
import com.example.feature_home.domain.model.BestSellerDomain
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.model.HotSalesDomain

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        title = title,
        description = description,
        imagePreview = imagePreview,
        image = images.first(),
        price = price,
        subcategories = subcategories.joinToString(","),
        category = category
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        title = title,
        description = description,
        imagePreview = imagePreview,
        image = image,
        price = price.toString(),
        subcategories = subcategories,
        category = category
    )
}

fun DomainDataSource.toHotSalesDomain(): HotSalesDomain {
    return  HotSalesDomain(
        id = id,
        title = title,
        description = description,
        imagePreview = imagePreview
    )
}

fun DomainDataSource.toBestSellerDomain(): BestSellerDomain {
    return BestSellerDomain(
        id = id,
        title = title,
        image = image,
        price = price
    )
}
