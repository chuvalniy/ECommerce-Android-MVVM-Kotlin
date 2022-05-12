package com.example.feature_main_screen.presentation.main_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Constants
import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.use_case.FetchBasketInfoUseCase
import com.example.feature_main_screen.domain.use_case.FetchMainScreenItemsUseCase
import com.example.feature_main_screen.presentation.main_screen.utils.CartScreenEvent
import com.example.feature_main_screen.presentation.main_screen.utils.MainScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val fetchMainScreenItemsUseCase: FetchMainScreenItemsUseCase,
    private val fetchBasketInfoUseCase: FetchBasketInfoUseCase
) : ViewModel() {

    private val _mainScreenUiEvent = MutableStateFlow<MainScreenEvent>(MainScreenEvent.Empty)
    val mainScreenUiEvent get() = _mainScreenUiEvent.asStateFlow()

    private val _cartUiEvent = MutableStateFlow<CartScreenEvent>(CartScreenEvent.Empty)
    val cartUiEvent get() = _cartUiEvent.asStateFlow()

    init {
        fetchMainScreenItems()
        fetchCartInfo()
    }

    private fun fetchCartInfo() = viewModelScope.launch {
        _cartUiEvent.value = CartScreenEvent.Loading
        when (val response = fetchBasketInfoUseCase()) {
            is Resource.Success -> {
                response.data?.let { data ->
                    _cartUiEvent.value = CartScreenEvent.Success(data = data)
                }
            }
        }
    }

    private fun fetchMainScreenItems() = viewModelScope.launch {
        _mainScreenUiEvent.value = MainScreenEvent.Loading
        when (val response = fetchMainScreenItemsUseCase()) {
            is Resource.Success -> {
                response.data?.let { data ->
                    _mainScreenUiEvent.value = MainScreenEvent.Success(data = data)
                }
            }
            is Resource.Error -> {
                _mainScreenUiEvent.value = MainScreenEvent.Failure(
                    error = response.error ?: Constants.ERROR_MESSAGE
                )
            }
            else -> Unit

        }
    }

}