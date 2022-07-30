package com.example.feature_main.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.ui.BaseFragment
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.feature_main.presentation.view_model.MainViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModel<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController =
            (childFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment).navController

        binding.bottomNavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavBar.isVisible =
                destination.id == com.example.feature_main_screen.R.id.home_screen
        }

        val badge = binding.bottomNavBar.getOrCreateBadge(R.id.nav_graph_cart)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.data.collect { state ->
                badge.number = state.currentCartItems
                badge.isVisible = state.isCartVisible
            }
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)
}