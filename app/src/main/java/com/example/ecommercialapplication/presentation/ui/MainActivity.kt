package com.example.ecommercialapplication.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ecommercialapplication.R
import com.example.ecommercialapplication.databinding.ActivityMainBinding
import com.example.ecommercialapplication.presentation.view_model.MainScreenViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainScreenViewModel>()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ECommercialApplication)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavBar.isVisible =
                destination.id == com.example.feature_main_screen.R.id.home_screen
        }


        val badge = binding.bottomNavBar.getOrCreateBadge(R.id.nav_graph_cart)

        lifecycleScope.launchWhenStarted {
            viewModel.data.collect { state ->
                badge.number = state.currentCartItems
                badge.isVisible = state.isCartVisible
            }
        }
    }
}