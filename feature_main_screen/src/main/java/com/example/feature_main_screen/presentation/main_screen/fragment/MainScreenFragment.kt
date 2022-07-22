package com.example.feature_main_screen.presentation.main_screen.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.core.utils.Constants
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.FragmentMainBinding
import com.example.feature_main_screen.presentation.main_screen.adapters.BestSellerAdapter
import com.example.feature_main_screen.presentation.main_screen.adapters.HotSalesAdapter
import com.example.feature_main_screen.presentation.main_screen.utils.SampleData.categories
import com.example.feature_main_screen.presentation.main_screen.view_model.MainScreenState
import com.example.feature_main_screen.presentation.main_screen.view_model.MainScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModel<MainScreenViewModel>()
    private val glide by inject<RequestManager>()

    private var bestSellerAdapter: BestSellerAdapter? = null
    private var hotSalesAdapter: HotSalesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()
        setupSpinnerLocationAdapter()
        setupAdapter()

        observeUiState()
        observeUiEffect()

        processButtonClicks()
    }

    private fun processButtonClicks() {
        binding.btnFilter.setOnClickListener { viewModel.filterButtonClicked() }
        binding.btnShoppingBag.setOnClickListener { viewModel.cartButtonClicked() }
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
            processUiState(state)
        }
    }

    private fun processUiState(state: MainScreenState) {
        processBestSellers(state)
        processHotSales(state)
        processCartInfo(state)
    }

    private fun processBestSellers(state: MainScreenState) {
        bestSellerAdapter?.items = state.bestSellers
    }

    private fun processHotSales(state: MainScreenState) {
        if (state.homeStoreInfo.isEmpty()) return

        val carouselHotSales =
            listOf(state.homeStoreInfo.last()) + state.homeStoreInfo + listOf(state.homeStoreInfo.first())
        hotSalesAdapter?.items = carouselHotSales
        onInfinitePageChangeCallback(carouselHotSales.size)
    }

    private fun processCartInfo(state: MainScreenState) {
        state.numberOfItemsInTheCart?.let {
            binding.tvNumberOfItems.text = it.toString()
            binding.tvNumberOfItems.isVisible = true
        }
    }

    private fun setupAdapter() {
        bestSellerAdapter = BestSellerAdapter(glide) { viewModel.productClicked() }
        hotSalesAdapter = HotSalesAdapter(glide)

        binding.rvBestSeller.adapter = bestSellerAdapter
        binding.vpHotSales.adapter = hotSalesAdapter
    }

    private fun onInfinitePageChangeCallback(
        listSize: Int
    ) = binding.vpHotSales.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)

            if (state == ViewPager2.SCROLL_STATE_IDLE) {
                when (binding.vpHotSales.currentItem) {
                    listSize - 1 -> binding.vpHotSales.setCurrentItem(1, false)
                    0 -> binding.vpHotSales.setCurrentItem(listSize - 2, false)
                }
            }
        }
    })

    private fun setupTabLayout() = categories.onEachIndexed { index, (title, image) ->
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(title))
        binding.tabLayout.getTabAt(index)?.apply {
            setCustomView(R.layout.tab_item)
            customView?.findViewById<ImageButton>(R.id.tabIcon)
                ?.setImageResource(image)
            customView?.findViewById<TextView>(R.id.tabTitle)?.text = title
        }
    }

    private fun setupSpinnerLocationAdapter() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.locations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_location_text_view)
            binding.spinnerLocation.adapter = adapter
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)
}