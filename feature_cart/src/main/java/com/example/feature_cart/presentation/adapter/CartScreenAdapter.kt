package com.example.feature_cart.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseDiffUtilItemCallback
import com.example.core.ui.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CartScreenAdapter(
    private val glide: RequestManager
) : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(CartScreenAdapterDelegate.basketAdapterDelegate(glide))
    }
}