package com.example.feature_search.presentation.epoxy.models

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.SearchTopBarBinding

data class SearchTopBarModel(
    private val onBackButtonClick: () -> Unit,
    private val onFilterButtonClick: () -> Unit
) : ViewBindingKotlinModel<SearchTopBarBinding>(R.layout.search_top_bar) {

    override fun SearchTopBarBinding.bind() {
        btnGoBack.setOnClickListener { onBackButtonClick() }
        btnFilter.setOnClickListener { onFilterButtonClick() }

    }
}

