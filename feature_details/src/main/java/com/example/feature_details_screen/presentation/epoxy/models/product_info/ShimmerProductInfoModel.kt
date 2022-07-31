package com.example.feature_details_screen.presentation.epoxy.models.product_info

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.facebook.shimmer.ShimmerFrameLayout

@EpoxyModelClass
abstract class ShimmerProductInfoModel : EpoxyModelWithHolder<ShimmerProductInfoModel.ShimmerProductInfoHolder>() {

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_details_product_info
    }

    override fun bind(holder: ShimmerProductInfoHolder) {
        holder.shimmerLayoutProductInfo.startShimmer()
    }

    override fun unbind(holder: ShimmerProductInfoHolder) {
        holder.shimmerLayoutProductInfo.stopShimmer()
    }

    class ShimmerProductInfoHolder : KotlinEpoxyHolder() {
        val shimmerLayoutProductInfo by bind<ShimmerFrameLayout>(R.id.productInfoShimmerLayout)
    }
}