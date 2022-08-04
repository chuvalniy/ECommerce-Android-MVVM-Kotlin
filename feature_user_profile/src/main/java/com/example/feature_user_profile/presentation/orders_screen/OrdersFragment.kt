package com.example.feature_user_profile.presentation.orders_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_user_profile.databinding.FragmentOrdersBinding

class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =  FragmentOrdersBinding.inflate(inflater, container, false)

}