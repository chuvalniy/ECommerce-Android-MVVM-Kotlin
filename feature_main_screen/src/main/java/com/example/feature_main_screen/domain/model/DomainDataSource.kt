package com.example.feature_main_screen.domain.model

data class DomainDataSource(
    val bestSellers: List<BestSellerDomain>,
    val hotSales: List<HotSalesDomain>,
    val cartInfo: Int? = null
)