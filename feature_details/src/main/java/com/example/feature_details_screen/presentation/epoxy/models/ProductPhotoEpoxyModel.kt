package com.example.feature_details_screen.presentation.epoxy.models

import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.ModelProductPhotoBinding

data class ProductPhotoEpoxyModel(
    val glide: RequestManager,
    val imageUrl: String
) : ViewBindingKotlinModel<ModelProductPhotoBinding>(R.layout.model_product_photo) {

    override fun ModelProductPhotoBinding.bind() {
        glide.load(imageUrl).into(ivProduct)
    }
}