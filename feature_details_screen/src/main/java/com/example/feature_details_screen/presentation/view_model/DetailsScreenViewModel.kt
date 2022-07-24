package com.example.feature_details_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_details_screen.domain.use_case.FetchProductDetailsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val fetchProductDetailsUseCase: FetchProductDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsScreenState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiChannel = Channel<UiEffect>()
    val uiEffect get() = _uiChannel.receiveAsFlow()

    init {
        fetchProductDetails()
    }

    private fun fetchProductDetails() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isLoading = true)

        when (val response = fetchProductDetailsUseCase()) {
            is Resource.Success -> {
                _uiState.value = _uiState.value.copy(data = response.data)
            }
            is Resource.Error -> {
                _uiState.value = _uiState.value.copy(isLoading = false)
                showSnackbar(response.error ?: "Error")
            }
            else -> Unit
        }
    }

    fun addToCartButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToCartScreen)
    }

    fun backButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateBack)
    }

    private suspend fun showSnackbar(message: String) {
           _uiChannel.send(UiEffect.ShowSnackbar(message))
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        object NavigateToCartScreen : UiEffect()
        object NavigateBack : UiEffect()
    }
}