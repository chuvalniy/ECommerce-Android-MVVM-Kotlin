package com.example.feature_main_screen.presentation.main_screen.view_model

import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HomeStoreDomain

data class MainScreenState(
    val bestSellers: List<BestSellerDomain> = emptyList(),
    val homeStoreInfo: List<HomeStoreDomain> = emptyList(),
    val isLoading: Boolean = false,
    val numberOfItemsInTheCart: Int? = null
)