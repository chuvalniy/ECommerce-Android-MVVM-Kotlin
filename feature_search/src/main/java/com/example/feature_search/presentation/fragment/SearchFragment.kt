package com.example.feature_search.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.feature_search.R
import com.example.feature_search.databinding.FragmentSearchBinding
import com.example.feature_search.presentation.epoxy.SearchEpoxyController
import com.example.feature_search.presentation.view_model.SearchState
import com.example.feature_search.presentation.view_model.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel by sharedViewModel<SearchViewModel>()

    private val glide by inject<RequestManager>()

    private var epoxyController: SearchEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeUiState()
        observeUiEffect()
    }

    private fun setupAdapter() {
        epoxyController = SearchEpoxyController(
            glide,
            onQueryTextListener = { query -> viewModel.queryTextChanged(query) },
            onBackButtonClick = { viewModel.backButtonClicked() },
            onProductClick = { id -> viewModel.productClicked(id) },
            onFilterButtonClick = { viewModel.filterButtonClicked() },
            onBrandClick = { viewModel.brandSelected(it) }
        ).also {
            binding.epoxyRecyclerView.setController(it)
        }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is SearchViewModel.UiEffect.NavigateBack -> {
                        findNavController().popBackStack()
                    }
                    is SearchViewModel.UiEffect.NavigateToDetail -> {
                        findNavController().navigate(Uri.parse("myApp://featureDetails/${effect.id}"))
                    }
                    is SearchViewModel.UiEffect.ShowSnackbar -> {
                        Snackbar.make(requireView(), effect.message, Snackbar.LENGTH_SHORT).show()
                    }
                    is SearchViewModel.UiEffect.NavigateToFilters -> {
                        findNavController().navigate(R.id.action_search_to_filterBottomDialogFragment)
                    }
                }
            }
        }
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

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

}