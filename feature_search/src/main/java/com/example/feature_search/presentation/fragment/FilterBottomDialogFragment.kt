package com.example.feature_search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.feature_search.R
import com.example.feature_search.databinding.FragmentFilterBottomDialogBinding
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

        observeUiEffect()

        val array = resources.getStringArray(R.array.rangeSliderValues)

        val categorySpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            array
        )

        val brandSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            array
        )

        val priceSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            array
        )

        binding.spinnerCategory.adapter = categorySpinnerAdapter
        binding.spinnerBrand.adapter = brandSpinnerAdapter
        binding.spinnerPrice.adapter = priceSpinnerAdapter

        processButtonClicks()
    }

    private fun processButtonClicks() {
        binding.btnGoBack.setOnClickListener { viewModel.backButtonClicked() }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is SearchViewModel.UiEffect.NavigateBack -> {
                        findNavController().popBackStack()
                    }
                    is SearchViewModel.UiEffect.ShowSnackbar -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}