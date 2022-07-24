package com.example.feature_main_screen.presentation.main_screen.view_model

import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HotSalesDomain

data class MainScreenState(
    val bestSellers: List<BestSellerDomain> = emptyList(),
    val hotSales: List<HotSalesDomain> = emptyList(),
    val isLoading: Boolean = false,
    val numberOfItemsInTheCart: Int = 0
)