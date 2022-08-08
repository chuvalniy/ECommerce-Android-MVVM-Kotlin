package com.example.feature_home.presentation.home_screen.view_model

import com.example.feature_home.domain.model.BestSellerDomain
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.model.HotSalesDomain

data class HomeScreenState(
    val bestSellers: List<BestSellerDomain> = emptyList(),
    val hotSales: List<HotSalesDomain> = emptyList(),
    val currentlySelectedCategory: Int = 0,
    val isLoading: Boolean = false,
)