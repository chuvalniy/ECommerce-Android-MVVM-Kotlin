package com.example.ecommercialapplication.data.mapper

import com.example.ecommercialapplication.data.remote.dto.CloudDataSource
import com.example.ecommercialapplication.domain.model.DomainDataSource

fun CloudDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        itemsInTheCart = basket.size
    )
}
