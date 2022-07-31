package com.example.feature_details_screen.presentation.epoxy.models.color_picker

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.facebook.shimmer.ShimmerFrameLayout

@EpoxyModelClass
abstract class ShimmerColorPickerModel : EpoxyModelWithHolder<ShimmerColorPickerModel.ShimmerColorPickerHolder>(){

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_details_color_picker
    }

    override fun bind(holder: ShimmerColorPickerHolder) {
        holder.shimmerLayoutColorPicker.startShimmer()
    }

    override fun unbind(holder: ShimmerColorPickerHolder) {
        holder.shimmerLayoutColorPicker.stopShimmer()
    }

    class ShimmerColorPickerHolder : KotlinEpoxyHolder() {
        val shimmerLayoutColorPicker by bind<ShimmerFrameLayout>(R.id.colorPickerShimmerLayout)
    }

}