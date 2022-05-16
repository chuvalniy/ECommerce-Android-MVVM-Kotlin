package com.example.feature_main_screen.presentation.main_screen.adapters

import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseDiffUtilItemCallback
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.domain.model.BestSellerDomain
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class BestSellerAdapter(
    private val glide: RequestManager,
    private val onGoToDetails: (BestSellerDomain) -> Unit
) : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(
            MainScreenAdapterDelegate.bestSellerAdapterDelegate(glide, onGoToDetails)
        )
    }
}