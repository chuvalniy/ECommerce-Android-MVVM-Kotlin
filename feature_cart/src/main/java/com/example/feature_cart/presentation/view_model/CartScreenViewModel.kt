package com.example.feature_cart.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_cart.domain.repository.CartScreenRepository
import com.example.feature_cart.presentation.utils.CartScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartScreenViewModel(
    private val repository: CartScreenRepository
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<CartScreenEvent>(CartScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        fetchCartInfo()
    }

    private fun fetchCartInfo() {
        viewModelScope.launch {
            _uiEvent.value = CartScreenEvent.Loading
            when (val response = repository.fetchCartInfo()) {
                is Resource.Success -> {
                    response.data?.let { it ->
                        _uiEvent.value = CartScreenEvent.Success(data = it)
                    }
                }
                is Resource.Error -> {
                    _uiEvent.value = CartScreenEvent.Failure(
                        error = response.error ?: "Unexpected error occurred"
                    )
                }
                else -> Unit
            }
        }
    }
}