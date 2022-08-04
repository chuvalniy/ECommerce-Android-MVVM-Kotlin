package com.example.feature_home.presentation.home_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.R
import com.example.feature_home.databinding.FragmentFilterBottomDialogBinding
import com.example.feature_home.presentation.home_screen.view.SampleData.brandFilter
import com.example.feature_home.presentation.home_screen.view.SampleData.priceFilter
import com.example.feature_home.presentation.home_screen.view.SampleData.sizeFilter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomDialogFragment : BottomSheetDialogFragment() {

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

        setupAdapter()

        applyBinding()
    }

    private fun applyBinding() = binding.apply {
        btnFilterDismiss.setOnClickListener { this@FilterBottomDialogFragment.dismiss() }
    }

    private fun setupAdapter() {
        val brandSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            brandFilter
        )
        val priceSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            priceFilter
        )
        val sizeSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            sizeFilter
        )

        binding.spinnerBrand.adapter = brandSpinnerAdapter
        binding.spinnerPrice.adapter = priceSpinnerAdapter
        binding.spinnerSize.adapter = sizeSpinnerAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}