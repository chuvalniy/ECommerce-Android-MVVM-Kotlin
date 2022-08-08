package com.example.feature_home.domain.use_case

import com.example.feature_home.data.mapper.toBestSellerDomain
import com.example.feature_home.domain.model.BestSellerDomain
import com.example.feature_home.domain.model.DomainDataSource

class FilterBestSellerDomain {

    fun execute(data: List<DomainDataSource>): List<BestSellerDomain> {
        return data
            .filter { it.subcategories.contains("best_seller") }
            .map { it.toBestSellerDomain() }
    }
}