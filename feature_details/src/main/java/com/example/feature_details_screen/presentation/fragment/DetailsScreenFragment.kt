package com.example.feature_details_screen.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.core.utils.Constants
import com.example.feature_details_screen.databinding.FragmentDetailsScreenBinding
import com.example.feature_details_screen.presentation.epoxy.DetailsScreenEpoxyController
import com.example.feature_details_screen.presentation.view_model.DetailsScreenViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsScreenFragment : BaseFragment<FragmentDetailsScreenBinding>() {

    private val viewModel by stateViewModel<DetailsScreenViewModel>(state = {
        val bundle = Bundle()
        bundle.putString("id", arguments?.getString("id"))
        bundle
    })

    private val glide by inject<RequestManager>()

    private var epoxyController: DetailsScreenEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeUiState()
        observeUiEffect()
    }

    private fun setupAdapter() {
        epoxyController = DetailsScreenEpoxyController(
            requireContext(),
            glide,
            onAddToCartButtonClick = { viewModel.addToCartButtonClicked() },
            onBackButtonClick = { viewModel.backButtonClicked() },
        ).also {
            binding.epoxyAdapter.setController(it)
        }
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is DetailsScreenViewModel.UiEffect.NavigateToCartScreen -> {
                    findNavController().navigate(Uri.parse("myApp://featureCart"))
                }
                is DetailsScreenViewModel.UiEffect.NavigateBack -> {
                    findNavController().popBackStack()
                }
                is DetailsScreenViewModel.UiEffect.ShowSnackbar -> {
                    Snackbar.make(requireView(), effect.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.uiState.collect { state ->
            epoxyController?.setData(state)
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsScreenBinding.inflate(inflater, container, false)
}