package com.example.feature_details_screen.presentation.epoxy

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.ModelProductInfoBinding
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.presentation.utils.SampleData

data class ProductInfoEpoxyModel(
    val productDetails: ProductDetailsDomain
): ViewBindingKotlinModel<ModelProductInfoBinding>(R.layout.model_product_info) {

    override fun ModelProductInfoBinding.bind() {
        tvTitle.text = productDetails.title
        tvCpu.text = productDetails.cpu
        tvCamera.text = productDetails.camera
        tvSdCapacity.text = productDetails.sd
        tvSsdCapacity.text = productDetails.ssd
        tvPrice.text = root.context.getString(R.string.product_price, productDetails.price)
        btnAddToFavorites.isChecked = productDetails.isFavorites

        rbMemoryLeft.text = root.context.getString(R.string.product_capacity, productDetails.capacity[0])
        rbMemoryRight.text = root.context.getString(R.string.product_capacity, productDetails.capacity[1])

        rbColorLeft.background.colorFilter =
            PorterDuffColorFilter(Color.parseColor(productDetails.color[0]), PorterDuff.Mode.SRC_IN)

        rbColorRight.background.colorFilter =
            PorterDuffColorFilter(Color.parseColor(productDetails.color[1]), PorterDuff.Mode.SRC_IN)

        SampleData.categories.forEach { title ->
            tlCategories.addTab(tlCategories.newTab().setText(title))
        }
    }
}
