package com.example.feature_search.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_search.presentation.epoxy.models.*
import com.example.feature_search.presentation.view_model.SearchState

class SearchEpoxyController(
    private val glide: RequestManager,
    private val onQueryTextListener: (String) -> Unit,
    private val onProductClick: (String) -> Unit,
    private val onBackButtonClick: () -> Unit,
    private val onFilterButtonClick: () -> Unit,
    private val onBrandClick: (String) -> Unit
) : TypedEpoxyController<SearchState>() {

    override fun buildModels(state: SearchState?) {
        SearchTopBarModel(onBackButtonClick)
            .id("serach_top_bar")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        SearchQueryAndFilterModel(onQueryTextListener, onFilterButtonClick)
            .id("search_query_and_filter")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        SearchCategoryChipModel(onBrandClick)
            .id("search_category_chip")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        SearchHeaderModel()
            .id("search_header")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        if (state?.isLoading == true) {
            repeat(8) { index ->
                ShimmerItemModel(index)
                    .id("shimmer_best_seller_$index")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            state.data.forEachIndexed { index, item ->
                SearchItemModel(item, glide, index, onProductClick)
                    .id("best_sellers_${item.id}")
                    .addTo(this)
            }
        }

    }
}