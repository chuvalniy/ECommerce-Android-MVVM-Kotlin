package com.example.feature_main_screen.domain.model

data class MainScreenDomain(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)