package com.example.ecommercialapplication.feature_main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommercialapplication.databinding.FragmentFilterBottomDialogBinding
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}