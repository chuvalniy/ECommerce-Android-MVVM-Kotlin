package com.example.feature_details_screen.presentation.fragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.core.utils.Constants
import com.example.feature_details_screen.R
import com.example.feature_details_screen.databinding.FragmentDetailsScreenBinding
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.presentation.adapter.DetailsScreenViewPagerAdapter
import com.example.feature_details_screen.presentation.utils.SampleData.categories
import com.example.feature_details_screen.presentation.view_model.DetailsScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class DetailsScreenFragment : BaseFragment<FragmentDetailsScreenBinding>() {

    private val viewModel by viewModel<DetailsScreenViewModel>()
    private var adapter: DetailsScreenViewPagerAdapter? = null
    private val glide by inject<RequestManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPagerWithCompositePagerTransformer()
        setupTabLayout()

        observeUiState()
        observeUiEffect()

        processButtonClicks()
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is DetailsScreenViewModel.UiEffect.NavigateToCartScreen -> {
                    findNavController().navigate(Uri.parse(Constants.CART_SCREEN_DEEP_LINK))
                }
                is DetailsScreenViewModel.UiEffect.ShowSnackbar -> TODO()
            }
        }
    }

    private fun processButtonClicks() = binding.apply {
        btnAddToCart.setOnClickListener { viewModel.addToCartButtonClicked() }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.uiState.collect { state ->
            state.data?.let { bindData(productDetails = it) }
            adapter?.submitList(state.data?.images)
        }
    }

    private fun setupViewPagerWithCompositePagerTransformer() {
        adapter = DetailsScreenViewPagerAdapter(glide)
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
    }

    private fun bindData(productDetails: ProductDetailsDomain) = binding.apply {
        ratingBar.rating = productDetails.rating
        tvTitle.text = productDetails.title
        tvCpu.text = productDetails.cpu
        tvCamera.text = productDetails.camera
        tvSdCapacity.text = productDetails.sd
        tvSsdCapacity.text = productDetails.ssd
        tvPrice.text = getString(R.string.product_price, productDetails.price)
        btnAddToFavorites.isChecked = productDetails.isFavorites

        rbMemoryLeft.text = getString(R.string.product_capacity, productDetails.capacity[0])
        rbMemoryRight.text = getString(R.string.product_capacity, productDetails.capacity[1])

        rbColorLeft.background.colorFilter =
            PorterDuffColorFilter(Color.parseColor(productDetails.color[0]), PorterDuff.Mode.SRC_IN)

        rbColorRight.background.colorFilter =
            PorterDuffColorFilter(Color.parseColor(productDetails.color[1]), PorterDuff.Mode.SRC_IN)
    }

    private fun setupTabLayout() = categories.forEach { title ->
        binding.tlCategories.addTab(binding.tlCategories.newTab().setText(title))
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsScreenBinding.inflate(inflater, container, false)
}