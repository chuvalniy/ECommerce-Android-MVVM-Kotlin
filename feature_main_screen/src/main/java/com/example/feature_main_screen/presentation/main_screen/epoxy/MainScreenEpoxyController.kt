package com.example.feature_main_screen.presentation.main_screen.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.Typed2EpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.example.feature_main_screen.domain.model.HomeStoreDomain

class MainScreenEpoxyController(
    private val glide: RequestManager
) : Typed2EpoxyController<List<BestSellerDomain>, List<HomeStoreDomain>>() {

    override fun buildModels(bestSellers: List<BestSellerDomain>?, hotSales: List<HomeStoreDomain>?) {
//        if (bestSeller.isNullOrEmpty() || hotSales.isNullOrEmpty()) return

        hotSales?.let {
            val items = hotSales.map { item ->
                HotSalesEpoxyModel(item, glide).id(item.id)
            }
            CarouselModel_()
                .id(hotSalesId)
                .models(items)
                .numViewsToShowOnScreen(1F)
                .addTo(this)
        }

        bestSellers?.let { items ->
            items.forEach { bestSeller ->
                BestSellerEpoxyModel(bestSeller, glide).id(bestSellerId).addTo(this)
            }
        }

    }

    companion object {
        const val hotSalesId = "hot_sales_carousel"
        const val bestSellerId = "best_seller"
    }
}