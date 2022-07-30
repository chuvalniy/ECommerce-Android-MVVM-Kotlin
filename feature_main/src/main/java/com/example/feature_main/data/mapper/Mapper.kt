package com.example.feature_main.data.mapper

import com.example.feature_main.data.remote.dto.CloudDataSource
import com.example.feature_main.domain.model.DomainDataSource


fun CloudDataSource.toDomainDataSource(): DomainDataSource {
    return DomainDataSource(
        itemsInTheCart = basket.size
    )
}
