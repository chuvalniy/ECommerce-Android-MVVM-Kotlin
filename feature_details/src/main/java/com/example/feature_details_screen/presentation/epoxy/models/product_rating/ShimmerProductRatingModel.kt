package com.example.feature_details_screen.presentation.epoxy.models.product_rating

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.facebook.shimmer.ShimmerFrameLayout

@EpoxyModelClass
abstract class ShimmerProductRatingModel : EpoxyModelWithHolder<ShimmerProductRatingModel.ShimmerProductRatingHolder>(){

    override fun bind(holder: ShimmerProductRatingHolder) {
        holder.shimmerLayoutRating.startShimmer()
    }

    override fun unbind(holder: ShimmerProductRatingHolder) {
        holder.shimmerLayoutRating.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_details_rating
    }

    class ShimmerProductRatingHolder : KotlinEpoxyHolder() {
        val shimmerLayoutRating by bind<ShimmerFrameLayout>(R.id.productRatingShimmerLayout)
    }
}