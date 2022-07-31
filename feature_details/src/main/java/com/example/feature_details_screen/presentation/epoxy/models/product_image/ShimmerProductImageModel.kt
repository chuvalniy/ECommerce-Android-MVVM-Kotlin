package com.example.feature_details_screen.presentation.epoxy.models.product_image

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.facebook.shimmer.ShimmerFrameLayout

@EpoxyModelClass
abstract class ShimmerProductImageModel : EpoxyModelWithHolder<ShimmerProductImageModel.ShimmerProductPhotoHolder>(){

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_details_image
    }

    override fun bind(holder: ShimmerProductPhotoHolder) {
        holder.shimmerLayoutPhoto.startShimmer()
    }

    override fun unbind(holder: ShimmerProductPhotoHolder) {
        holder.shimmerLayoutPhoto.stopShimmer()
    }

    class ShimmerProductPhotoHolder : KotlinEpoxyHolder() {
        val shimmerLayoutPhoto by bind<ShimmerFrameLayout>(R.id.productImageShimmerLayout)
    }
}