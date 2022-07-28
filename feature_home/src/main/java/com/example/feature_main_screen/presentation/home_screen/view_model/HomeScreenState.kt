package com.example.feature_main_screen.presentation.home_screen.view_model

import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HotSalesDomain

data class HomeScreenState(
    val bestSellers: List<BestSellerDomain> = emptyList(),
    val hotSales: List<HotSalesDomain> = emptyList(),
    val isLoading: Boolean = false,
)