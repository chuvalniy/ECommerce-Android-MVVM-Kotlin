package com.example.feature_main_screen.domain.model

data class DomainDataSource(
    val bestSeller: List<BestSellerDomain>,
    val homeStore: List<HomeStoreDomain>,
    val cartInfo: Int? = null
)