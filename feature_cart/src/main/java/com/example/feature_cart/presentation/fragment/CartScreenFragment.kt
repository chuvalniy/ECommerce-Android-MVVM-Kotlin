package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.feature_cart.R
import com.example.feature_cart.databinding.FragmentCartScreenBinding
import com.example.feature_cart.domain.model.CartDomain
import com.example.feature_cart.presentation.adapter.CartScreenAdapter
import com.example.feature_cart.presentation.utils.CartScreenEvent
import com.example.feature_cart.presentation.view_model.CartScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CartScreenFragment : BaseFragment<FragmentCartScreenBinding>() {

    private val viewModel by viewModel<CartScreenViewModel>()

    private val glide by inject<RequestManager>()

    private lateinit var adapter: CartScreenAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartScreenAdapter(glide)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is CartScreenEvent.Success -> {
                        adapter.submitList(event.data.basket)
                        binding.rvCart.adapter = adapter
                        bindData(event.data)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun bindData(cartDomain: CartDomain) {
        binding.tvTotal.text = getString(R.string.total_price, cartDomain.total)
        binding.tvDelivery.text = cartDomain.delivery
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartScreenBinding.inflate(inflater, container, false)
}