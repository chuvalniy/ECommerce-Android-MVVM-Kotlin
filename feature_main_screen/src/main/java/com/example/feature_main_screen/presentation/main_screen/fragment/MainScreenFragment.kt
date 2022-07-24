package com.example.feature_main_screen.presentation.main_screen.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.core.utils.Constants
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.FragmentMainBinding
import com.example.feature_main_screen.presentation.main_screen.epoxy.MainScreenEpoxyController
import com.example.feature_main_screen.presentation.main_screen.view_model.MainScreenState
import com.example.feature_main_screen.presentation.main_screen.view_model.MainScreenViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModel<MainScreenViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: MainScreenEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpoxyController()
//        setupTabLayout()

        observeUiState()
        observeUiEffect()

        processButtonClick()
    }

    private fun processButtonClick() {
        binding.btnShoppingBag.setOnClickListener { viewModel.cartButtonClicked() }
    }

    private fun setupEpoxyController() {
        epoxyController = MainScreenEpoxyController(
            glide,
            onFilterButtonClick = { viewModel.filterButtonClicked() },
            onProductClick = { viewModel.productClicked() }
        )
        binding.epoxyRecyclerView.setController(epoxyController!!)
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is MainScreenViewModel.UiEffect.ShowSnackbar -> {
                    Snackbar.make(requireView(), effect.message, Snackbar.LENGTH_LONG).show()
                }
                is MainScreenViewModel.UiEffect.NavigateToCartScreen -> {
                    findNavController().navigate(Uri.parse(Constants.CART_SCREEN_DEEP_LINK))
                }
                is MainScreenViewModel.UiEffect.NavigateToDetailsScreen -> {
                    findNavController().navigate(Uri.parse(Constants.DETAILS_SCREEN_DEEP_LINK))
                }
                is MainScreenViewModel.UiEffect.NavigateToFilterDialogScreen -> with(
                    findNavController()
                ) {
                    if (currentDestination?.id == R.id.home_screen) {
                        navigate(R.id.action_home_screen_to_filter_screen)
                    }
                }
            }
        }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.mainScreenState.collect { state ->
            processUiState(state)
        }
    }

    private fun processUiState(state: MainScreenState) {
        epoxyController?.setData(state.bestSellers, state.hotSales)

        if (state.numberOfItemsInTheCart > 0) {
            binding.tvNumberOfItems.text = state.numberOfItemsInTheCart.toString()
            binding.tvNumberOfItems.isVisible = true
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)
}