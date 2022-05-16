package com.example.feature_main_screen.presentation.main_screen.adapters

import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseDiffUtilItemCallback
import com.example.core.ui.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class HotSalesAdapter(
    private val glide: RequestManager
) : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(MainScreenAdapterDelegate.hotSalesAdapterDelegate(glide))
    }
}