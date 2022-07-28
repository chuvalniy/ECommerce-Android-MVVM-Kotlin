package com.example.feature_main_screen.presentation.home_screen.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_main_screen.presentation.home_screen.epoxy.model.*
import com.example.feature_main_screen.presentation.home_screen.view_model.HomeScreenState

class HomeScreenEpoxyController(
    private val glide: RequestManager,
    private val onFilterButtonClick: () -> Unit,
    private val onProductClick: () -> Unit
) : TypedEpoxyController<HomeScreenState>() {

    override fun buildModels(
        state: HomeScreenState?
    ) {

        // Top bar
        TopBarEpoxyModel(onFilterButtonClick)
            .id("top_bar")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Select category header
        HeaderEpoxyModel(header = HeaderType.CATEGORY)
            .id(HeaderType.CATEGORY.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        ProductCategoryEpoxyModel()
            .id("product_categories")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Search
        SearchEpoxyModel()
            .id("search_bar")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Hot sales header
        HeaderEpoxyModel(header = HeaderType.HOT_SALES)
            .id(HeaderType.HOT_SALES.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        if (state?.isLoading == true) {
            processLoadingState()
        } else if (state?.isLoading == false) {
            processSuccessState(state)
        }
    }

    private fun processSuccessState(state: HomeScreenState) {
        val models = state.hotSales.map { item ->
            HotSalesEpoxyModel(item, glide).id(item.id)
        }
        CarouselModel_()
            .id("hot_sales")
            .models(models)
            .numViewsToShowOnScreen(1F)
            .addTo(this)


        // Best sellers header
        HeaderEpoxyModel(header = HeaderType.BEST_SELLERS)
            .id(HeaderType.BEST_SELLERS.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Best sellers content
        state.bestSellers.forEachIndexed { index, bestSeller ->
            BestSellerEpoxyModel(bestSeller, index, onProductClick, glide)
                .id("best_sellers_${bestSeller.id}")
                .addTo(this)
        }
    }

    private fun processLoadingState() {
        val hotSalesShimmerFirst = ShimmerHotSalesEpoxyModel()
            .id("shimmer_hot_sales")

        CarouselModel_()
            .id("shimmer_hot_sales_carousel")
            .models(listOf(hotSalesShimmerFirst))
            .numViewsToShowOnScreen(1F)
            .addTo(this)

        HeaderEpoxyModel(header = HeaderType.BEST_SELLERS)
            .id(HeaderType.BEST_SELLERS.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        repeat(4) {
            ShimmerBestSellerEpoxyModel(it)
                .id("shimmer_best_seller_$it")
                .addTo(this)
        }
    }
}
