package com.example.ecommercialapplication.feature_main.domain.model

data class MainScreenDomain(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)