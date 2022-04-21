package com.example.ecommercialapplication.feature_main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercialapplication.core.utils.Resource
import com.example.ecommercialapplication.feature_main.domain.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ShopRepository
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<MainScreenEvent>(MainScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        getShopItems()
    }

    private fun getShopItems() {
        viewModelScope.launch {
            _uiEvent.value = MainScreenEvent.Loading
            when (val response = repository.fetchShopItems()) {
                is Resource.Success -> {
                    _uiEvent.value = MainScreenEvent.Success(data = response.data!!)
                }
                is Resource.Error -> {
                    _uiEvent.value = MainScreenEvent.Failure(
                        error = response.error ?: "Unexpected error occurred"
                    )
                }
                else -> Unit
            }
        }
    }
}