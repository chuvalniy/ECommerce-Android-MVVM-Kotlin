package com.example.feature_cart.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Constants
import com.example.core.utils.Resource
import com.example.feature_cart.domain.use_case.FetchCartInfoUseCase
import com.example.feature_cart.presentation.view_model.model.CartScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartScreenViewModel(
    private val fetchCartInfoUseCase: FetchCartInfoUseCase
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<CartScreenEvent>(CartScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        fetchCartInfo()
    }

    private fun fetchCartInfo() = viewModelScope.launch {
        _uiEvent.value = CartScreenEvent.Loading
        when (val response = fetchCartInfoUseCase()) {
            is Resource.Success -> {
                response.data?.let { it ->
                    _uiEvent.value = CartScreenEvent.Success(data = it)
                }
            }
            is Resource.Error -> {
                _uiEvent.value = CartScreenEvent.Failure(
                    error = response.error ?: Constants.ERROR_MESSAGE
                )
            }
            else -> Unit
        }
    }
}