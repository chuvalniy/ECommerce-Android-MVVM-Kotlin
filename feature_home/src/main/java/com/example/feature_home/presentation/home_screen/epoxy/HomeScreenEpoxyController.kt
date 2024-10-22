package com.example.feature_home.presentation.home_screen.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_home.presentation.home_screen.epoxy.model.*
import com.example.feature_home.presentation.home_screen.utils.SampleData
import com.example.feature_home.presentation.home_screen.view_model.HomeScreenState

class HomeScreenEpoxyController(
    private val glide: RequestManager,
    private val onProductClick: (String) -> Unit,
    private val onCategoryClick: (Int) -> Unit,
    private val onSearchClick: () -> Unit
) : TypedEpoxyController<HomeScreenState>() {

    override fun buildModels(state: HomeScreenState?) {
        // Top bar
        TopBarEpoxyModel()
            .id("top_bar")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Select category header
        HeaderEpoxyModel(header = HeaderType.CATEGORY)
            .id(HeaderType.CATEGORY.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        val categoryModels = SampleData.categories.mapIndexed { index, item ->
            ProductCategoryEpoxyModel(
                item,
                state?.currentlySelectedCategory!!,
                index,
                onCategoryClick
            ).id("category_${item.title}")
        }
        CarouselModel_()
            .id("product_categories")
            .models(categoryModels)
            .addTo(this)

        // Search
        SearchEpoxyModel(onSearchClick)
            .id("search_bar")
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Hot sales header
        HeaderEpoxyModel(header = HeaderType.HOT_SALES)
            .id(HeaderType.HOT_SALES.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Hot Sales content
        if (state?.isLoading == true || state?.hotSales?.isEmpty() == true) {
            val hotSalesShimmer = ShimmerHotSalesEpoxyModel()
                .id("shimmer_hot_sales")

            CarouselModel_()
                .id("shimmer_hot_sales_carousel")
                .models(listOf(hotSalesShimmer))
                .numViewsToShowOnScreen(1F)
                .addTo(this)
        } else if (state?.isLoading == false) {
            val models = state.hotSales.map { item ->
                HotSalesEpoxyModel(item, glide).id(item.id)
            }
            CarouselModel_()
                .id("hot_sales")
                .models(models)
                .addTo(this)
        }

        // Best sellers header
        HeaderEpoxyModel(header = HeaderType.BEST_SELLERS)
            .id(HeaderType.BEST_SELLERS.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Best seller content
        if (state?.isLoading == true || state?.bestSellers?.isEmpty() == true) {
            repeat(4) {
                ShimmerBestSellerEpoxyModel(it)
                    .id("shimmer_best_seller_$it")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            // Best sellers content
            state.bestSellers.forEachIndexed { index, bestSeller ->
                BestSellerEpoxyModel(bestSeller, index, onProductClick, glide)
                    .id("best_sellers_${bestSeller.id}")
                    .addTo(this)
            }
        }
    }
}
