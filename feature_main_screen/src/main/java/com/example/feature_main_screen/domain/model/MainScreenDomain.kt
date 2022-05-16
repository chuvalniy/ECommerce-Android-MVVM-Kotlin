package com.example.feature_main_screen.domain.model

data class MainScreenDomain(
    val best_seller: List<BestSellerDomain>,
    val home_store: List<HomeStoreDomain>
)