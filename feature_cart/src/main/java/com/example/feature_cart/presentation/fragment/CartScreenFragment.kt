package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.core.ui.BaseFragment
import com.example.feature_cart.databinding.FragmentCartScreenBinding
import com.example.feature_cart.presentation.epoxy.CartScreenEpoxyController
import com.example.feature_cart.presentation.view_model.CartScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartScreenFragment : BaseFragment<FragmentCartScreenBinding>() {

    private val viewModel by viewModel<CartScreenViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: CartScreenEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeCart()
    }

    private fun setupAdapter() {
        epoxyController = CartScreenEpoxyController(glide)
        binding.epoxyRecylerView.setController(epoxyController!!)
    }

    private fun observeCart() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.uiState.collect { state ->
            epoxyController?.setData(state.cartInfo)
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartScreenBinding.inflate(inflater, container, false)
}