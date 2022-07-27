package com.example.feature_details_screen.data.mapper

import android.util.Log
import com.example.feature_details_screen.data.local.entity.CapacityEntity
import com.example.feature_details_screen.data.local.entity.ColorEntity
import com.example.feature_details_screen.data.local.entity.ImageEntity
import com.example.feature_details_screen.data.local.entity.ProductDetailsEntity
import com.example.feature_details_screen.data.remote.dto.ProductDetailsDto
import com.example.feature_details_screen.domain.model.ProductDetailsDomain

fun ProductDetailsDto.toProductDetailsEntity(): ProductDetailsEntity {
    return ProductDetailsEntity(
        cpu = cpu,
        camera = camera,
        capacity = capacity.map { CapacityEntity(it) },
        color = color.map { ColorEntity(it) },
        id = id,
        images = images.map { ImageEntity(it) },
        isFavorites = isFavorites,
        price = price,
        rating = rating,
        sd = sd,
        ssd = ssd,
        title = title
    )
}

fun ProductDetailsEntity.toProductDetailsDomain(): ProductDetailsDomain {
    return ProductDetailsDomain(
        cpu = cpu,
        camera = camera,
        capacity = capacity.map { it.capacity },
        color = color.map { it.color },
        id = id.toInt(),
        images = images.map { it.image },
        isFavorites = isFavorites,
        price = price.toString(),
        rating = rating.toFloat(),
        sd = sd,
        ssd = ssd,
        title = title
    )
}