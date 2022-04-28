package com.example.ecommercialapplication.feature_main.presentation.main_screen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommercialapplication.R
import com.example.ecommercialapplication.core.BaseFragment
import com.example.ecommercialapplication.databinding.FragmentMainBinding
import com.example.ecommercialapplication.feature_main.presentation.filter_bottom_dialog.FilterBottomDialogFragment
import com.example.ecommercialapplication.feature_main.presentation.main_screen.adapters.MainScreenRecyclerViewAdapter
import com.example.ecommercialapplication.feature_main.presentation.main_screen.adapters.MainScreenViewPagerAdapter
import com.example.ecommercialapplication.feature_main.presentation.utils.ExampleData.categories
import com.example.ecommercialapplication.feature_main.presentation.utils.ExampleData.locations
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainScreenViewModel by viewModels()

    private lateinit var recyclerViewAdapter: MainScreenRecyclerViewAdapter
    private lateinit var viewPagerAdapter: MainScreenViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAdapter = MainScreenRecyclerViewAdapter()
        val filterBottomDialogFragment = FilterBottomDialogFragment()
        val locationSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_location_text_view,
            locations
        )

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> {
                        event.data.let {
                            recyclerViewAdapter.submitList(event.data.best_seller)
                            viewPagerAdapter = MainScreenViewPagerAdapter(event.data.home_store)
                            binding.vpHotSales.adapter = viewPagerAdapter
                            onInfinitePageChangeCallback(event.data.home_store.size + 2)
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
        binding.rvBestSeller.adapter = recyclerViewAdapter
        binding.spinnerLocation.adapter = locationSpinnerAdapter

        setupTabLayout()
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

    private fun setupTabLayout() {
        val categoryTitles = ArrayList(categories.keys)
        val imageResIds = ArrayList(categories.values)

        categoryTitles.forEachIndexed { index, title ->
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(title))

            binding.tabLayout.getTabAt(index)?.apply {
                setCustomView(R.layout.tab_item)
                customView?.findViewById<ImageButton>(R.id.tabIcon)
                    ?.setImageResource(imageResIds[index])
                customView?.findViewById<TextView>(R.id.tabTitle)?.text = categoryTitles[index]
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