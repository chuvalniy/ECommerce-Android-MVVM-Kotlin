package com.example.feature_search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.feature_search.databinding.FragmentFilterBottomDialogBinding
import com.example.feature_search.presentation.view_model.PriceFilter
import com.example.feature_search.presentation.view_model.SearchState
import com.example.feature_search.presentation.view_model.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FilterBottomDialogFragment : BottomSheetDialogFragment() {

    private val viewModel by sharedViewModel<SearchViewModel>()

    private var _binding: FragmentFilterBottomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState()

        processUiEvent()
    }

    private fun processUiEvent() {
        binding.rbAll.setOnClickListener { viewModel.priceSelected(PriceFilter.ALL) }
        binding.rbPriceLowest.setOnClickListener { viewModel.priceSelected(PriceFilter.LOWEST) }
        binding.rbPriceAverage.setOnClickListener { viewModel.priceSelected(PriceFilter.AVERAGE) }
        binding.rbPriceHighest.setOnClickListener { viewModel.priceSelected(PriceFilter.HIGHEST) }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: SearchState) {
        when (state.priceFilter) {
            PriceFilter.ALL -> binding.rbAll.isChecked = true
            PriceFilter.LOWEST -> binding.rbPriceLowest.isChecked = true
            PriceFilter.AVERAGE -> binding.rbPriceAverage.isChecked = true
            PriceFilter.HIGHEST -> binding.rbPriceHighest.isChecked = true
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}