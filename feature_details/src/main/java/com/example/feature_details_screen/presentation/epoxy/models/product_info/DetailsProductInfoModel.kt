package com.example.feature_details_screen.presentation.epoxy.models.product_info

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R

@EpoxyModelClass
abstract class DetailsProductInfoModel : EpoxyModelWithHolder<DetailsProductInfoModel.ProductInfoFeatureHolder>(){

    @EpoxyAttribute
    lateinit var cpu: String

    @EpoxyAttribute
    lateinit var camera: String

    @EpoxyAttribute
    lateinit var sdCapacity: String

    @EpoxyAttribute
    lateinit var ram: String

    override fun bind(holder: ProductInfoFeatureHolder) {
        holder.textViewCpu.text = cpu
        holder.textViewCamera.text = camera
        holder.textViewSdCapacity.text = sdCapacity
        holder.textViewRam.text = ram

    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_product_info
    }

    class ProductInfoFeatureHolder : KotlinEpoxyHolder() {
        val textViewCpu by bind<TextView>(R.id.tvCpu)
        val textViewCamera by bind<TextView>(R.id.tvCamera)
        val textViewSdCapacity by bind<TextView>(R.id.tvSdCapacity)
        val textViewRam by bind<TextView>(R.id.tvRam)
    }
}