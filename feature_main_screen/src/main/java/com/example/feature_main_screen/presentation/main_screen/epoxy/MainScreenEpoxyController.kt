package com.example.feature_main_screen.presentation.main_screen.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.Typed2EpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HotSalesDomain
import com.example.feature_main_screen.presentation.main_screen.epoxy.model.*

class MainScreenEpoxyController(
    private val glide: RequestManager,
    private val onFilterButtonClick: () -> Unit,
    private val onProductClick: () -> Unit
) : Typed2EpoxyController<List<BestSellerDomain>, List<HotSalesDomain>>() {

    override fun buildModels(
        bestSellers: List<BestSellerDomain>?,
        hotSales: List<HotSalesDomain>?
    ) {
//        if (bestSeller.isNullOrEmpty() || hotSales.isNullOrEmpty()) return

        // Top bar
        TopBarEpoxyModel(onFilterButtonClick)
            .id(TOP_BAR_ID)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Select category header
        HeaderEpoxyModel(header = HeaderType.CATEGORY)
            .id(HeaderType.CATEGORY.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Search
        SearchEpoxyModel()
            .id(SEARCH_ID)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Hot sales header
        HeaderEpoxyModel(header = HeaderType.HOT_SALES)
            .id(HeaderType.HOT_SALES.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Hot sales content
        hotSales?.let {
            val items = hotSales.map { item ->
                HotSalesEpoxyModel(item, glide).id(item.id)
            }
            CarouselModel_()
                .id(HOT_SALES_ID)
                .models(items)
                .numViewsToShowOnScreen(1F)
                .addTo(this)
        }

        // Best sellers header
        HeaderEpoxyModel(header = HeaderType.BEST_SELLERS)
            .id(HeaderType.BEST_SELLERS.name)
            .spanSizeOverride { _, _, _ -> 2 }
            .addTo(this)

        // Best sellers content
        bestSellers?.let { items ->
            items.forEach { bestSeller ->
                BestSellerEpoxyModel(bestSeller, onProductClick, glide)
                    .id(BEST_SELLER_ID)
                    .addTo(this)
            }
        }

    }

    companion object {
        const val HOT_SALES_ID = "hot_sales_carousel"
        const val BEST_SELLER_ID = "best_seller"
        const val SEARCH_ID = "search"
        const val TOP_BAR_ID = "top_bar"
    }

}
