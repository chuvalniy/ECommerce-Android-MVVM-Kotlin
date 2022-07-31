package com.example.feature_details_screen.presentation.epoxy.models.product_image

import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.RequestManager
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R

@EpoxyModelClass
abstract class DetailsProductImageModel : EpoxyModelWithHolder<DetailsProductImageModel.ProductPhotoHolder>() {

    @EpoxyAttribute
    lateinit var imageUrl: String

    @EpoxyAttribute
    lateinit var glide: RequestManager

    override fun bind(holder: ProductPhotoHolder) {
        glide.load(imageUrl).into(holder.photo)
    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_image
    }

    class ProductPhotoHolder : KotlinEpoxyHolder() {
        val photo by bind<ImageView>(R.id.ivProduct)
    }
}