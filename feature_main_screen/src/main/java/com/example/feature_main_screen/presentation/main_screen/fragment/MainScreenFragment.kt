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
import com.example.feature_main_screen.domain.model.BestSeller
import com.example.feature_main_screen.domain.model.HomeStore
import com.example.feature_main_screen.presentation.main_screen.adapters.MainScreenBestSellerAdapter
import com.example.feature_main_screen.presentation.main_screen.adapters.MainScreenHotSalesAdapter
import com.example.feature_main_screen.presentation.main_screen.bottom_dialog_filter.FilterBottomDialogFragment
import com.example.feature_main_screen.presentation.main_screen.utils.CartScreenEvent
import com.example.feature_main_screen.presentation.main_screen.utils.MainScreenEvent
import com.example.feature_main_screen.presentation.main_screen.utils.SampleData
import com.example.feature_main_screen.presentation.main_screen.utils.SampleData.categories
import com.example.feature_main_screen.presentation.main_screen.view_model.MainScreenViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModel<MainScreenViewModel>()

    private val glide by inject<RequestManager>()

    private lateinit var bestSellerAdapter: MainScreenBestSellerAdapter
    private lateinit var hotSalesAdapter: MainScreenHotSalesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filterBottomDialogFragment = FilterBottomDialogFragment()

        bestSellerAdapter = MainScreenBestSellerAdapter(glide) {
            findNavController().navigate(Uri.parse(Constants.DETAILS_SCREEN_DEEP_LINK))
        }
        hotSalesAdapter = MainScreenHotSalesAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.mainScreenUiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> {
                        bindMainScreenData(
                            bestSeller = event.data.best_seller,
                            homeStore = event.data.home_store
                        )
                    }
                    else -> Unit
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.cartUiEvent.collect { event ->
                when (event) {
                    is CartScreenEvent.Success -> {
                        binding.tvNumberOfItems.text = event.numberOfItems
                        binding.tvNumberOfItems.isVisible = event.shouldShowBadge
                    }
                    else -> Unit
                }
            }
        }

        binding.btnFilter.setOnClickListener { showBottomDialogFragment(filterBottomDialogFragment) }
        binding.btnShoppingBag.setOnClickListener {
            findNavController().navigate(Uri.parse(Constants.CART_SCREEN_DEEP_LINK))
        }

        setupTabLayout()
        setupSpinnerLocationAdapter()
    }

    private fun bindMainScreenData(
        bestSeller: List<BestSeller>,
        homeStore: List<HomeStore>
    ) {
        // bestSeller
        bestSellerAdapter.submitList(bestSeller)
        binding.rvBestSeller.adapter = bestSellerAdapter

        // homeStore
        val carouselHotSales = listOf(homeStore.last()) + homeStore + listOf(homeStore.first())
        hotSalesAdapter.submitList(carouselHotSales)
        onInfinitePageChangeCallback(carouselHotSales.size)
        binding.vpHotSales.adapter = hotSalesAdapter
    }

    private fun onInfinitePageChangeCallback(listSize: Int) {
        binding.vpHotSales.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
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
    }

    private fun setupTabLayout() = categories.onEachIndexed { index, (title, image) ->
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(title))
        binding.tabLayout.getTabAt(index)?.apply {
            setCustomView(R.layout.tab_item)
            customView?.findViewById<ImageButton>(R.id.tabIcon)
                ?.setImageResource(image)
            customView?.findViewById<TextView>(R.id.tabTitle)?.text = title
        }
    }

    private fun showBottomDialogFragment(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (bottomSheetDialogFragment.isAdded) {
            return
        }
        bottomSheetDialogFragment.show(parentFragmentManager, "FilterBottomSheetDialog")
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