package com.example.feature_home.presentation.home_screen.epoxy.model

import androidx.core.content.ContextCompat
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_home.R
import com.example.feature_home.databinding.CategoryItemBinding
import com.example.feature_home.presentation.home_screen.utils.CategoryItem

data class ProductCategoryEpoxyModel(
    val category: CategoryItem,
    val currentlySelectedCategory: Int,
    val currentIndex: Int,
    val onCategoryClick: (Int) -> Unit
) : ViewBindingKotlinModel<CategoryItemBinding>(R.layout.category_item) {
    override fun CategoryItemBinding.bind() {
        tvCategory.text = category.title
        ivCategory.setImageResource(category.icon)

        if (currentlySelectedCategory == currentIndex) {
            tvCategory.setTextColor(ContextCompat.getColor(root.context, R.color.light_orange))
            ivCategory.setColorFilter(ContextCompat.getColor(root.context, R.color.white))
            cvCategory.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.light_orange))
        } else {
            tvCategory.setTextColor(ContextCompat.getColor(root.context, R.color.dark_blue))
            ivCategory.setColorFilter(ContextCompat.getColor(root.context, R.color.gray))
            cvCategory.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.white))
        }

        root.setOnClickListener { onCategoryClick(currentIndex) }
    }
}