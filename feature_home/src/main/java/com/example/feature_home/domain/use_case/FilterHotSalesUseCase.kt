package com.example.feature_home.domain.use_case

import com.example.feature_home.data.mapper.toHotSalesDomain
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.model.HotSalesDomain

class FilterHotSalesUseCase {


    fun execute(data: List<DomainDataSource>): List<HotSalesDomain> {
        return data
            .filter { it.subcategories.contains("hot_sales") }
            .map { it.toHotSalesDomain() }
    }
}