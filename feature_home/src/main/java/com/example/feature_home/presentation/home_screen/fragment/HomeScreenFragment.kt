package com.example.feature_home.presentation.home_screen.fragment

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
import com.example.feature_home.R
import com.example.feature_home.databinding.FragmentHomeBinding
import com.example.feature_home.presentation.home_screen.epoxy.HomeScreenEpoxyController
import com.example.feature_home.presentation.home_screen.view_model.HomeScreenState
import com.example.feature_home.presentation.home_screen.view_model.HomeScreenViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeScreenFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeScreenViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: HomeScreenEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpoxyController()

        observeUiState()
        observeUiEffect()
    }

    private fun setupEpoxyController() {
        epoxyController = HomeScreenEpoxyController(
            glide,
            onFilterButtonClick = { viewModel.filterButtonClicked() },
            onProductClick = { viewModel.productClicked() },
            onCategoryClick = { viewModel.categorySelected(it) }
        )
        with(binding.epoxyRecyclerView) {
            setController(epoxyController!!)
        }
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is HomeScreenViewModel.UiEffect.ShowSnackbar -> {
                    Snackbar.make(requireView(), effect.message, Snackbar.LENGTH_LONG).show()
                }
                is HomeScreenViewModel.UiEffect.NavigateToDetailsScreen -> {
                    findNavController().navigate(Uri.parse(Constants.DETAILS_SCREEN_DEEP_LINK))
                }
                is HomeScreenViewModel.UiEffect.NavigateToFilterDialogScreen -> with(
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
        viewModel.uiState.collect { state ->
            processUiState(state)
        }
    }

    private fun processUiState(state: HomeScreenState) {
        epoxyController?.setData(state)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)
}