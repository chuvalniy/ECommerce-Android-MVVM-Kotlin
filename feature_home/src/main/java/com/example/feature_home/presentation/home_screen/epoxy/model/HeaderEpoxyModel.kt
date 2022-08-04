package com.example.feature_home.presentation.home_screen.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.ModelHeaderBinding

data class HeaderEpoxyModel(
    val header: HeaderType,
) : ViewBindingKotlinModel<ModelHeaderBinding>(R.layout.model_header) {

    override fun ModelHeaderBinding.bind() {
        when (header) {
            HeaderType.CATEGORY -> {
                tvTitle.text = root.context.getString(R.string.select_category)
                btnSeeMore.text = root.context.getString(R.string.view_all)
            }
            HeaderType.BEST_SELLERS -> {
                tvTitle.text = root.context.getString(R.string.best_seller)
                btnSeeMore.text = root.context.getString(R.string.see_more)
            }
            HeaderType.HOT_SALES -> {
                tvTitle.text = root.context.getString(R.string.hot_sales)
                btnSeeMore.text = root.context.getString(R.string.see_more)
            }
        }
    }
}

enum class HeaderType {
    CATEGORY, HOT_SALES, BEST_SELLERS
}