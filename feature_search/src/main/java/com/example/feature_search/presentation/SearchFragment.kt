package com.example.feature_search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_search.databinding.FragmentSearchBinding


class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)
}