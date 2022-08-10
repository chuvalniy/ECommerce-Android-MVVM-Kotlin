package com.example.feature_search.presentation.epoxy.models

import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.core.helpers.ViewBindingKotlinModel
import com.example.feature_search.R
import com.example.feature_search.databinding.SearchItemBinding
import com.example.feature_search.domain.model.DomainDataSource

class SearchItemModel(
    private val item: DomainDataSource,
    private val glide: RequestManager,
    private val index: Int,
    private val onProductClick: (String) -> Unit
) : ViewBindingKotlinModel<SearchItemBinding>(R.layout.search_item) {

    override fun SearchItemBinding.bind() {
        setupMargins()

        tvTitle.text = item.title
        tvPrice.text = root.resources.getString(R.string.formatted_product_price, item.price)
        glide.load(item.image).into(ivItem)

        root.setOnClickListener { onProductClick(item.id) }
    }

    private fun SearchItemBinding.setupMargins() {
        val params: ViewGroup.MarginLayoutParams =
            cvItem.layoutParams as ViewGroup.MarginLayoutParams
        when (index % 2) {
            0 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium)
            )
            1 -> params.setMargins(
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin),
                root.context.resources.getDimensionPixelSize(R.dimen.card_view_margin_medium)
            )
        }
        cvItem.layoutParams = params
    }
}