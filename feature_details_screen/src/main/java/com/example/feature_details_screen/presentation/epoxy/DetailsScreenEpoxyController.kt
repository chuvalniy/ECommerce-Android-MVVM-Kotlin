package com.example.feature_details_screen.presentation.epoxy

import androidx.lifecycle.Transformations.map
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.presentation.epoxy.models.*
import com.example.feature_details_screen.presentation.view_model.DetailsScreenState
import kotlin.random.Random

class DetailsScreenEpoxyController(
    private val glide: RequestManager,
    private val onBackButtonClick: () -> Unit,
    private val onAddToCartButtonClick: () -> Unit
) : TypedEpoxyController<DetailsScreenState>() {

    override fun buildModels(state: DetailsScreenState?) {

        DetailsTopBarEpoxyModel(onBackButtonClick)
            .id("top_bar")
            .addTo(this)

        if (state?.isLoading == true) {
            processLoadingState()
        } else if (state?.isLoading == false) {
            processSuccessState(state)
        }
    }

    private fun processSuccessState(state: DetailsScreenState) {
        val photos = state.data.images.map {
            ProductPhotoEpoxyModel(glide, it).id(Random.nextInt())
        }
        CarouselModel_()
            .id("carousel_photo")
            .models(photos)
            .addTo(this)

        ProductInfoEpoxyModel(state.data, onAddToCartButtonClick)
            .id("product_info")
            .addTo(this)
    }

    private fun processLoadingState() {
        val productPhoto = ShimmerProductPhotoEpoxyModel().id("shimmer_product_photo")

        CarouselModel_()
            .id("shimmer_carousel_photo")
            .models(listOf(productPhoto))
            .addTo(this)

        ShimmerProductInfoEpoxyModel().id("shimmer_product_info").addTo(this)
    }

}