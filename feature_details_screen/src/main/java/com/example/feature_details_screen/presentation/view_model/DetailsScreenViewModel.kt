package com.example.feature_details_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_details_screen.domain.model.ProductDetailsDomain
import com.example.feature_details_screen.domain.use_case.FetchDetailsScreenDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val fetchDetailsScreenDataSource: FetchDetailsScreenDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsScreenState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiChannel = Channel<UiEffect>()
    val uiEffect get() = _uiChannel.receiveAsFlow()

    init {
        fetchProductDetails()
    }

    private fun fetchProductDetails() = viewModelScope.launch {
        fetchDetailsScreenDataSource().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    processSuccessState(result)
                }
                is Resource.Error -> {
                    processErrorState(result)
                }
                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                }
            }
        }.launchIn(this)

    }

    private suspend fun processErrorState(response: Resource<ProductDetailsDomain>) {
        _uiState.value = _uiState.value.copy(isLoading = false)
        showSnackbar(response.error ?: "Error")
    }

    private fun processSuccessState(response: Resource<ProductDetailsDomain>) {
        response.data?.let { product ->
            _uiState.value = _uiState.value.copy(
                data = product,
                isLoading = false
            )
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