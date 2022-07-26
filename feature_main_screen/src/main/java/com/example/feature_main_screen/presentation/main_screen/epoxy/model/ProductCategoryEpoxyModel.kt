package com.example.feature_main_screen.presentation.main_screen.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ModelCategoryBinding
import com.example.feature_main_screen.presentation.main_screen.view.SampleData

class ProductCategoryEpoxyModel : ViewBindingKotlinModel<ModelCategoryBinding>(R.layout.model_category) {
    override fun ModelCategoryBinding.bind() {
        categoryView.setTabs(SampleData.categories)
    }

}