package com.example.feature_search.presentation.epoxy.models

import com.example.core.extension.onQueryTextChanged
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.SearchQueryAndFilterBinding

data class SearchQueryAndFilterModel(
    private val onQueryTextListener: (String) -> Unit,
) : ViewBindingKotlinModel<SearchQueryAndFilterBinding>(R.layout.search_query_and_filter) {

    override fun SearchQueryAndFilterBinding.bind() {
        searchView.onQueryTextChanged(onQueryTextListener)
    }
}