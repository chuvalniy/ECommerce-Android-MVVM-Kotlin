package com.example.feature_search.presentation.epoxy.models

import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.SearchCategoryChipBinding
import com.google.android.material.chip.Chip

data class SearchCategoryChipModel(
    val onBrandClick: (String) -> Unit,
) : ViewBindingKotlinModel<SearchCategoryChipBinding>(R.layout.search_category_chip) {

    override fun SearchCategoryChipBinding.bind() {

        cgCategory.setOnCheckedStateChangeListener { _, _ ->
            val chip: Chip = cgCategory.findViewById(cgCategory.checkedChipId)
            onBrandClick(chip.text.toString())
        }

    }
}