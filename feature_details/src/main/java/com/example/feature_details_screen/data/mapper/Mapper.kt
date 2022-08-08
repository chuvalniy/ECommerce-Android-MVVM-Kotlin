package com.example.feature_details_screen.data.mapper

import com.example.feature_details_screen.data.local.entity.CacheDataSource
import com.example.feature_details_screen.data.local.entity.ImageEntity
import com.example.feature_details_screen.data.remote.dto.CloudDataSource
import com.example.feature_details_screen.domain.model.DomainDataSource

fun CloudDataSource.toCacheDataSource(): CacheDataSource {
    return CacheDataSource(
        id = id,
        title = title,
        description = description,
        images = images.map { ImageEntity(it) },
        price = price,
        rating = rating
    )
}

fun CacheDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        id = id,
        title = title,
        description = description,
        images = images.map { it.image },
        price = price.toString(),
        rating = rating
    )
}