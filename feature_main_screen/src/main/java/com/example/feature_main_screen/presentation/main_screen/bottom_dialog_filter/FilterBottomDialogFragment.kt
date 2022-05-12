package com.example.feature_main_screen.presentation.main_screen.bottom_dialog_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.R
import com.example.feature_main_screen.databinding.FragmentFilterBottomDialogBinding
import com.example.feature_main_screen.presentation.main_screen.utils.SampleData.brandFilter
import com.example.feature_main_screen.presentation.main_screen.utils.SampleData.priceFilter
import com.example.feature_main_screen.presentation.main_screen.utils.SampleData.sizeFilter
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

        binding.btnFilterDismiss.setOnClickListener {
            this.dismiss() // close dialog fragment
        }
        binding.spinnerBrand.adapter = brandSpinnerAdapter
        binding.spinnerPrice.adapter = priceSpinnerAdapter
        binding.spinnerSize.adapter = sizeSpinnerAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}