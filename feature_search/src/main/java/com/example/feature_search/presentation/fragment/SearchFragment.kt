package com.example.feature_search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.feature_search.databinding.FragmentSearchBinding
import com.example.feature_search.presentation.epoxy.SearchEpoxyController
import com.example.feature_search.presentation.view_model.SearchState
import com.example.feature_search.presentation.view_model.SearchViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel by viewModel<SearchViewModel>()

    private val glide by inject<RequestManager>()

    private var epoxyController: SearchEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: SearchState) {
        epoxyController?.setData(state)
    }

    private fun setupAdapter() {
        epoxyController = SearchEpoxyController(
            glide,
        ).also {
            binding.epoxyRecyclerView.setController(it)
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

}