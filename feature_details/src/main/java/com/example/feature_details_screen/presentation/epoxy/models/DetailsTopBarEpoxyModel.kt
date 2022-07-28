package com.example.feature_details_screen.presentation.epoxy.models

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.ModelTopBinding

data class DetailsTopBarEpoxyModel(
    val onBackButtonClick: () -> Unit
) : ViewBindingKotlinModel<ModelTopBinding>(R.layout.model_top) {

    override fun ModelTopBinding.bind() {
        btnGoBack.setOnClickListener { onBackButtonClick() }
    }
}