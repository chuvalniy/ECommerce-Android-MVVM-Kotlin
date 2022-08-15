package com.example.feature_search.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_search.presentation.epoxy.models.*
import com.example.feature_search.presentation.view_model.SearchState

class SearchEpoxyController(
    private val glide: RequestManager,
    private val onProductClick: (String) -> Unit,
) : TypedEpoxyController<SearchState>() {

    override fun buildModels(state: SearchState?) {
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