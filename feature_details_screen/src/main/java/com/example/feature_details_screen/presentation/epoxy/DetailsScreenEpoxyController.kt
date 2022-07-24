package com.example.feature_details_screen.presentation.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import kotlin.random.Random

class DetailsScreenEpoxyController(
    private val glide: RequestManager
) : TypedEpoxyController<ProductDetailsDomain>() {

    override fun buildModels(data: ProductDetailsDomain?) {

        TopBarEpoxyModel().id("top_bar").addTo(this)

        data?.images?.let { items ->
            val photos = items.map {
                ProductPhotoEpoxyModel(glide, it).id(Random.nextInt())
            }
            CarouselModel_()
                .id("PHOTO")
                .models(photos)
                .addTo(this)

            ProductInfoEpoxyModel(data).id("product_info").addTo(this)
        }

    }

}