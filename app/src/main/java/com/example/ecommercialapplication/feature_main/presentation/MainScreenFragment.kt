package com.example.ecommercialapplication.feature_main.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommercialapplication.R
import com.example.ecommercialapplication.core.BaseFragment
import com.example.ecommercialapplication.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainScreenViewModel by viewModels()

    private lateinit var adapter: MainScreenAdapter
    private lateinit var viewPagerAdapter: HomeStoreViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainScreenAdapter()
        viewPagerAdapter = HomeStoreViewPagerAdapter()
        val filterBottomDialogFragment = FilterBottomDialogFragment()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Success -> {
                        adapter.submitList(event.data.best_seller)
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
        binding.viewPager.adapter = viewPagerAdapter
        binding.rvBestSeller.adapter = adapter
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