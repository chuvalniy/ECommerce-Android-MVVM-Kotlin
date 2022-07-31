package com.example.feature_details_screen.presentation.epoxy.models.product_rating

import android.widget.RatingBar
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R

@EpoxyModelClass
abstract class DetailsProductRatingModel :
    EpoxyModelWithHolder<DetailsProductRatingModel.ProductInfoRatingHolder>() {

    @EpoxyAttribute
    var rating: Float? = null

    override fun bind(holder: ProductInfoRatingHolder) {
        holder.ratingBar.rating = rating ?: 0F
    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_rating
    }

    class ProductInfoRatingHolder : KotlinEpoxyHolder() {
        val ratingBar by bind<RatingBar>(R.id.ratingBar)
    }

}