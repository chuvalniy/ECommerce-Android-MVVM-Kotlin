package com.example.ecommercialapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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

        val badge = binding.bottomNavBar.getOrCreateBadge(R.id.cart)

            lifecycleScope.launchWhenStarted {
            viewModel.data.collect { state ->
                badge.number = state.currentCartItems
                badge.isVisible = state.isCartVisible
            }
        }
    }
}