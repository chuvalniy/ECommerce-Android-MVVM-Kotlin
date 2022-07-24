package com.example.feature_details_screen.presentation.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.presentation.epoxy.models.ProductInfoEpoxyModel
import com.example.feature_details_screen.presentation.epoxy.models.ProductPhotoEpoxyModel
import com.example.feature_details_screen.presentation.epoxy.models.DetailsTopBarEpoxyModel
import kotlin.random.Random

class DetailsScreenEpoxyController(
    private val glide: RequestManager,
    private val onBackButtonClick: () -> Unit,
    private val onAddToCartButtonClick: () -> Unit
) : TypedEpoxyController<ProductDetailsDomain>() {

    override fun buildModels(data: ProductDetailsDomain?) {

        DetailsTopBarEpoxyModel(onBackButtonClick)
            .id("top_bar")
            .addTo(this)


        data?.images?.let { items ->
            val photos = items.map {
                ProductPhotoEpoxyModel(glide, it).id(Random.nextInt())
            }
            CarouselModel_()
                .id("carousel_photo")
                .models(photos)
                .addTo(this)

            ProductInfoEpoxyModel(data, onAddToCartButtonClick)
                .id("product_info")
                .addTo(this)
        }

    }

}