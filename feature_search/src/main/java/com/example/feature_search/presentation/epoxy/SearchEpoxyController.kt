package com.example.feature_search.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_search.presentation.epoxy.models.SearchItemModel
import com.example.feature_search.presentation.epoxy.models.SearchQueryAndFilterModel
import com.example.feature_search.presentation.epoxy.models.SearchTopBarModel
import com.example.feature_search.presentation.view_model.SearchState

class SearchEpoxyController(
    private val glide: RequestManager,
) : TypedEpoxyController<SearchState>() {

    override fun buildModels(state: SearchState?) {
        SearchTopBarModel()
            .id("serach_top_bar")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        SearchQueryAndFilterModel()
            .id("search_query_and_filter")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        if (state?.isLoading == true || state?.data?.isEmpty() == true) {
            // TODO
        } else if (state?.isLoading == false) {
            state.data.forEachIndexed { index, item ->
                SearchItemModel(item, glide, index)
                    .id("best_sellers_${item.id}")
                    .addTo(this)
            }
        }

    }
}