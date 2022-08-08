package com.example.feature_details_screen.presentation.epoxy

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.bumptech.glide.RequestManager
import com.example.feature_details_screen.R
import com.example.feature_details_screen.presentation.epoxy.models.add_to_cart.detailsAddToCart
import com.example.feature_details_screen.presentation.epoxy.models.add_to_cart.shimmerAddToCart
import com.example.feature_details_screen.presentation.epoxy.models.category_tabs.detailsCategoryTabs
import com.example.feature_details_screen.presentation.epoxy.models.product_image.DetailsProductImageModel_
import com.example.feature_details_screen.presentation.epoxy.models.product_image.ShimmerProductImageModel_
import com.example.feature_details_screen.presentation.epoxy.models.product_info.detailsProductInfo
import com.example.feature_details_screen.presentation.epoxy.models.product_info.shimmerProductInfo
import com.example.feature_details_screen.presentation.epoxy.models.product_rating.detailsProductRating
import com.example.feature_details_screen.presentation.epoxy.models.product_rating.shimmerProductRating
import com.example.feature_details_screen.presentation.epoxy.models.product_title.detailsProductTitle
import com.example.feature_details_screen.presentation.epoxy.models.product_title.shimmerProductTitle
import com.example.feature_details_screen.presentation.epoxy.models.top_bar.detailsTopBar
import com.example.feature_details_screen.presentation.view_model.DetailsScreenState
import java.util.*

class DetailsScreenEpoxyController(
    private val context: Context,
    private val glide: RequestManager,
    private val onBackButtonClick: () -> Unit,
    private val onAddToCartButtonClick: () -> Unit,
    private val onGoToCartButtonClick: () -> Unit
) : TypedEpoxyController<DetailsScreenState>() {

    override fun buildModels(state: DetailsScreenState) {

        detailsTopBar {
            id("details_top_bar")
            onGoBackClick(this@DetailsScreenEpoxyController.onBackButtonClick)
            onGoToCartClick(this@DetailsScreenEpoxyController.onGoToCartButtonClick)
        }

        if (state.isLoading) {
            processLoadingState()
        } else {
            processSuccessState(state)
        }
    }

    private fun processSuccessState(state: DetailsScreenState) {
        val productImageModels = state.data.images.map {
            DetailsProductImageModel_()
                .id(UUID.randomUUID().toString())
                .glide(glide)
                .imageUrl(it)
        }

        carousel {
            id("product_photo_carousel")
            models(productImageModels)
        }

        group {
            id("product_info_group")
            layout(R.layout.vertical_linear_group)

            detailsProductTitle {
                id("details_product_title")
                productTitle(state.data.title)
            }

            detailsProductRating {
                id("details_product_rating")
                rating(state.data.rating)
            }

            detailsCategoryTabs { id("details_product_categories") }

            detailsProductInfo {
                id("details_product_features")
            }

            detailsAddToCart {
                id("details_add_to_cart")

                productPrice(
                    this@DetailsScreenEpoxyController.context.getString(
                        R.string.product_price, state.data.price
                    )
                )

                onAddToCartClick(this@DetailsScreenEpoxyController.onAddToCartButtonClick)
            }
        }
    }

    private fun processLoadingState() {
        val shimmerProductImage = ShimmerProductImageModel_().id("shimmer_product_image")

        carousel {
            id("shimmer_product_photo_carousel")
            models(listOf(shimmerProductImage))
        }

        group {
            id("shimmer_product_info_group")
            layout(R.layout.vertical_linear_group)

            shimmerProductTitle { id("shimmer_product_title") }

            shimmerProductRating { id("shimmer_rating") }

            detailsCategoryTabs { id("shimmer_details_product_categories") }

            shimmerProductInfo { id("shimmer_product_info") }

            shimmerAddToCart { id("shimmer_add_to_cart") }
        }
    }
}
