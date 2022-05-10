package com.example.feature_main_screen.presentation.main_screen.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.FragmentMainBinding
import com.example.feature_main_screen.presentation.main_screen.adapters.MainScreenBestSellerAdapter
import com.example.feature_main_screen.presentation.main_screen.adapters.MainScreenHotSalesAdapter
import com.example.feature_main_screen.presentation.main_screen.bottom_dialog_filter.FilterBottomDialogFragment
import com.example.feature_main_screen.presentation.main_screen.view_model.MainScreenViewModel
import com.example.feature_main_screen.presentation.utils.SampleData.categories
import com.example.feature_main_screen.presentation.utils.SampleData.locations
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainScreenFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModel<MainScreenViewModel>()

    private val glide by inject<RequestManager>()

    private val categoryTitles = ArrayList(categories.keys)
    private val categoryIcons = ArrayList(categories.values)

    private lateinit var bestSellerAdapter: MainScreenBestSellerAdapter
    private lateinit var hotSalesAdapter: MainScreenHotSalesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filterBottomDialogFragment = FilterBottomDialogFragment()

        val locationSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_location_text_view,
            locations
        )
        bestSellerAdapter = MainScreenBestSellerAdapter(glide)
        hotSalesAdapter = MainScreenHotSalesAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> {
                        event.data.let { data ->
                            bestSellerAdapter.submitList(data.best_seller)

                            val carouselHotSales =
                                listOf(data.home_store.last()) + data.home_store + listOf(data.home_store.first())
                            hotSalesAdapter.submitList(carouselHotSales)
                            onInfinitePageChangeCallback(data.home_store.size + 2)
                        }
                    }
                    is MainScreenEvent.Loading -> {
                        // show skeleton while loading data
                    }
                    is MainScreenEvent.Failure -> {
                        // show snackbar with error and continue to show skeleton
                    }
                    else -> Unit
                }
            }
        }

        binding.btnFilter.setOnClickListener {
            showBottomDialogFragment(filterBottomDialogFragment)
        }
        binding.spinnerLocation.adapter = locationSpinnerAdapter
        binding.rvBestSeller.adapter = bestSellerAdapter
        binding.vpHotSales.adapter = hotSalesAdapter

        setupTabLayout(categoryTitles, categoryIcons)
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

    private fun setupTabLayout(titles: List<String>, icons: List<Int>) {

        titles.forEachIndexed { index, title ->
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(title))

            binding.tabLayout.getTabAt(index)?.apply {
                setCustomView(R.layout.tab_item)
                customView?.findViewById<ImageButton>(R.id.tabIcon)
                    ?.setImageResource(icons[index])
                customView?.findViewById<TextView>(R.id.tabTitle)?.text = title
            }
        }
    }

    private fun showBottomDialogFragment(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        // prevent closing app with exception
        // when clicking multiple times at once on the filter button
        if (bottomSheetDialogFragment.isAdded) {
            return
        }
        bottomSheetDialogFragment.show(parentFragmentManager, "FilterBottomSheetDialog")
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)
}