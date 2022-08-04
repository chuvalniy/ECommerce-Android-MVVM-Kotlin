package com.example.feature_cart.presentation.fragment

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
import com.example.feature_cart.databinding.FragmentCartScreenBinding
import com.example.feature_cart.presentation.epoxy.CartScreenEpoxyController
import com.example.feature_cart.presentation.view_model.CartScreenState
import com.example.feature_cart.presentation.view_model.CartScreenViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartScreenFragment : BaseFragment<FragmentCartScreenBinding>() {

    private val viewModel by viewModel<CartScreenViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: CartScreenEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeUiState()
        observeUiEffects()

        processButtonClicks()
    }

    private fun processButtonClicks() {
        binding.topBar.btnGoBack.setOnClickListener { viewModel.backButtonPressed() }
    }

    private fun setupAdapter() {
        epoxyController = CartScreenEpoxyController(
            glide,
            onCheckoutButtonClick = { viewModel.checkoutButtonPressed() }
        ).also {
            binding.epoxyRecylerView.setController(it)
        }
    }

    private fun observeUiEffects() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is CartScreenViewModel.UiEffect.NavigateBack -> {
                        findNavController().popBackStack()
                    }
                    is CartScreenViewModel.UiEffect.NavigateToMainScreen -> {
                        with(findNavController()) {
                            popBackStack()
                            navigate(Uri.parse(Constants.HOME_SCREEN_DEEP_LINK))
                        }
                    }
                    is CartScreenViewModel.UiEffect.ShowSnackbar -> {
                        Snackbar.make(requireView(), effect.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.uiState.collect { state ->
            processUiState(state)
        }
    }

    private fun processUiState(state: CartScreenState) {
        epoxyController?.setData(state)
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartScreenBinding.inflate(inflater, container, false)
}