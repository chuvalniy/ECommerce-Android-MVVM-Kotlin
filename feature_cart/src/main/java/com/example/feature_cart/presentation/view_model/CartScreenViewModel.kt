package com.example.feature_cart.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Constants
import com.example.core.utils.Resource
import com.example.feature_cart.domain.use_case.FetchCartInfoUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CartScreenViewModel(
    private val fetchCartInfoUseCase: FetchCartInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartScreenState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiChannel = Channel<UiEvent>()
    val uiEvent get() = _uiChannel.receiveAsFlow()

    init {
        fetchCartInfo()
    }

    private fun fetchCartInfo() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isLoading = true)

        when (val response = fetchCartInfoUseCase()) {
            is Resource.Success -> {
                _uiState.value = _uiState.value.copy(cartInfo = response.data!!)
            }
            is Resource.Error -> {
                _uiState.value = _uiState.value.copy(isLoading = false)
                showSnackbar(response.error ?: "")
            }
            else -> Unit
        }
    }

    private suspend fun showSnackbar(message: String) {
        _uiChannel.send(UiEvent.ShowSnackbar(message))
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
    }
}