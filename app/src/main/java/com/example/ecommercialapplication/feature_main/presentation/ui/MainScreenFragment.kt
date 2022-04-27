package com.example.ecommercialapplication.feature_main.presentation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecommercialapplication.R
import com.example.ecommercialapplication.core.BaseFragment
import com.example.ecommercialapplication.databinding.FragmentMainBinding
import com.example.ecommercialapplication.feature_main.presentation.utils.MainScreenLists.categories
import com.example.ecommercialapplication.feature_main.presentation.utils.MainScreenLists.locations
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainScreenViewModel by viewModels()

    private lateinit var recyclerAdapter: MainScreenAdapter
    private lateinit var viewPagerAdapter: HomeStoreViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = MainScreenAdapter()
        viewPagerAdapter = HomeStoreViewPagerAdapter()
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
                        recyclerAdapter.submitList(event.data.best_seller)
                        viewPagerAdapter.submitList(event.data.home_store)
                    }
                    is MainScreenEvent.Loading -> {

                    }
                    is MainScreenEvent.Failure -> {

                    }
                    else -> Unit
                }
            }
        }

        binding.btnFilter.setOnClickListener {
            showBottomDialogFragment(filterBottomDialogFragment)
        }
        binding.vpHotSales.adapter = viewPagerAdapter
        binding.rvBestSeller.adapter = recyclerAdapter
        binding.spinnerLocation.adapter = locationSpinnerAdapter

        setupTabLayout()
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