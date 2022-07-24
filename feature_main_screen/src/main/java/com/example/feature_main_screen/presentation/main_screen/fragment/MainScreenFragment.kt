package com.example.feature_main_screen.presentation.main_screen.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

        processButtonClicks()
    }

    private fun setupEpoxyController() {
        epoxyController = MainScreenEpoxyController(glide)
        binding.epoxyRecyclerView.setController(epoxyController!!)
    }

    private fun processButtonClicks() {
//        binding.btnFilter.setOnClickListener { viewModel.filterButtonClicked() }
//        binding.btnShoppingBag.setOnClickListener { viewModel.cartButtonClicked() }
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is MainScreenViewModel.UiEffect.ShowSnackbar -> {

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
            epoxyController?.setData(state.bestSellers, state.homeStoreInfo)
        }
    }



    private fun processCartInfo(state: MainScreenState) {
//        state.numberOfItemsInTheCart?.let {
//            binding.tvNumberOfItems.text = it.toString()
//            binding.tvNumberOfItems.isVisible = true
//        }
    }



//    private fun setupTabLayout() = categories.onEachIndexed { index, (title, image) ->
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(title))
//        binding.tabLayout.getTabAt(index)?.apply {
//            setCustomView(R.layout.tab_item)
//            customView?.findViewById<ImageButton>(R.id.tabIcon)
//                ?.setImageResource(image)
//            customView?.findViewById<TextView>(R.id.tabTitle)?.text = title
//        }
//    }
//

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)
}