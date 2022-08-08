package com.example.feature_home.presentation.home_screen.epoxy.model

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.HotSalesItemBinding
import com.example.feature_home.domain.model.DomainDataSource
import com.example.feature_home.domain.model.HotSalesDomain

data class HotSalesEpoxyModel(
    val hotSales: HotSalesDomain,
    val glide: RequestManager
) : ViewBindingKotlinModel<HotSalesItemBinding>(R.layout.hot_sales_item) {

    override fun HotSalesItemBinding.bind() {
        tvTitleHomeStore.text = hotSales.title
        tvSubtitleHomeScreen.text = hotSales.description

        glide.load(hotSales.imagePreview).into(ivHotSales)
    }

}