package com.example.feature_main_screen.presentation.main_screen.epoxy

import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.HotSalesItemBinding
import com.example.feature_main_screen.domain.model.HomeStoreDomain

data class HotSalesEpoxyModel(
    val hotSales: HomeStoreDomain,
    val glide: RequestManager
) : ViewBindingKotlinModel<HotSalesItemBinding>(R.layout.hot_sales_item) {

    override fun HotSalesItemBinding.bind() {
        tvTitleHomeStore.text = hotSales.title
        tvSubtitleHomeScreen.text = hotSales.subtitle
        btnBuyNow.isVisible = hotSales.is_buy

        hotSales.is_new?.let { isNew ->
            tvNew.isVisible = isNew
        }

        glide.load(hotSales.picture).into(ivHotSales)
    }
}