package com.example.feature_details_screen.presentation.epoxy.models.category_tabs

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.core.helpers.KotlinEpoxyHolder
import com.example.feature_details_screen.R
import com.example.feature_details_screen.presentation.utils.SampleData
import com.google.android.material.tabs.TabLayout

@EpoxyModelClass
abstract class DetailsCategoryTabsModel : EpoxyModelWithHolder<DetailsCategoryTabsModel.ProductInfoCategoryHolder>(){

    override fun bind(holder: ProductInfoCategoryHolder) {
        holder.categoryTabLayout.removeAllTabs()
        SampleData.categories.forEach { title ->
            holder.categoryTabLayout.addTab(holder.categoryTabLayout.newTab().setText(title))
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.details_category_tab_layout
    }

    class ProductInfoCategoryHolder : KotlinEpoxyHolder() {
        val categoryTabLayout by bind<TabLayout>(R.id.tlCategories)
    }
}