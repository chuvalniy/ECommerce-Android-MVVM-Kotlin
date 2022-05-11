package com.example.feature_details_screen.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.FragmentDetailsScreenBinding
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.presentation.adapter.DetailsScreenViewPagerAdapter
import com.example.feature_details_screen.presentation.utils.DetailScreenEvent
import com.example.feature_details_screen.presentation.utils.SampleData
import com.example.feature_details_screen.presentation.view_model.DetailsScreenViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class DetailsScreenFragment : BaseFragment<FragmentDetailsScreenBinding>() {

    private val viewModel by viewModel<DetailsScreenViewModel>()

    lateinit var adapter: DetailsScreenViewPagerAdapter

    private val glide by inject<RequestManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DetailsScreenViewPagerAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is DetailScreenEvent.Success -> {
                        bindData(productDetails = event.data)
                        adapter.submitList(event.data.images) // create function and it here
                    }
                    is DetailScreenEvent.Loading -> {
                        // show skeleton and etc...
                    }
                    is DetailScreenEvent.Failure -> {
                        // show error and etc...
                    }
                    else -> Unit
                }
            }
        }

        // TODO: create function for viewpager
        binding.viewPager2.adapter = adapter

        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePagerTransformer = CompositePageTransformer()
        compositePagerTransformer.addTransformer(MarginPageTransformer(30))
        compositePagerTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }

        binding.viewPager2.setPageTransformer(compositePagerTransformer)

        setupTabLayout(SampleData.categories)
    }

    private fun bindData(productDetails: ProductDetailsDomain) {
        binding.ratingBar.rating = productDetails.rating
        binding.tvTitle.text = productDetails.title
        binding.tvCpu.text = productDetails.cpu
        binding.tvCamera.text = productDetails.camera
        binding.tvSdCapacity.text = productDetails.sd
        binding.tvSsdCapacity.text = productDetails.ssd
        binding.tvPrice.text = getString(R.string.product_price, productDetails.price)

        binding.btnAddToFavorites.isChecked = productDetails.isFavorites

        // TODO: replace with radio group
        binding.rbMemoryLeft.text = getString(R.string.product_capacity, productDetails.capacity[0])
        binding.rbMemoryRight.text = getString(R.string.product_capacity, productDetails.capacity[1])

        // TODO: create color picker
    }

    // TODO: ? replace this fun with xml tab items
    private fun setupTabLayout(titles: List<String>) {
        titles.forEach { title ->
            binding.tlCategories.addTab(binding.tlCategories.newTab().setText(title))
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsScreenBinding.inflate(inflater, container, false)
}