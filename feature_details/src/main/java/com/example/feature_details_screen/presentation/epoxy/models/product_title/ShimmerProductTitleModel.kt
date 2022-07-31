package com.example.feature_details_screen.presentation.epoxy.models.product_title

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.facebook.shimmer.ShimmerFrameLayout

@EpoxyModelClass
abstract class ShimmerProductTitleModel : EpoxyModelWithHolder<ShimmerProductTitleModel.ShimmerProductTitleHolder>(){

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_details_title
    }

    override fun bind(holder: ShimmerProductTitleHolder) {
        holder.shimmerLayoutProductTitle.startShimmer()
    }

    override fun unbind(holder: ShimmerProductTitleHolder) {
        holder.shimmerLayoutProductTitle.stopShimmer()
    }

    class ShimmerProductTitleHolder : KotlinEpoxyHolder() {
        val shimmerLayoutProductTitle by bind<ShimmerFrameLayout>(R.id.productTitleShimmerLayout)
    }
}