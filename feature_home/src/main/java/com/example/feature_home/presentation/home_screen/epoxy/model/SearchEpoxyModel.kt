package com.example.feature_home.presentation.home_screen.epoxy.model

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.ModelSearchBinding

data class SearchEpoxyModel(
    val onGoToSearch: () -> Unit
) : ViewBindingKotlinModel<ModelSearchBinding>(R.layout.model_search) {
    override fun ModelSearchBinding.bind() {
        cvSearch.setOnClickListener { onGoToSearch() }
    }
}