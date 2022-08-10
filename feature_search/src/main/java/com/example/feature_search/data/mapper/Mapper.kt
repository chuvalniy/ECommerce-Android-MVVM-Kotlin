package com.example.feature_search.data.mapper

import com.example.feature_search.data.local.entity.CacheDataSource
import com.example.feature_search.data.remote.dto.CloudDataSource
import com.example.feature_search.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        title = title,
        image = images.first(),
        price = price,
        subcategories = subcategories.joinToString(","),
        category = category,
        brand = brand,
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        title = title,
        image = image,
        price = price.toString(),
        brand = brand
    )
}