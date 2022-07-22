package com.example.feature_main_screen.presentation.main_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.use_case.FetchMainScreenItemsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val fetchMainScreenItemsUseCase: FetchMainScreenItemsUseCase
) : ViewModel() {

    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState get() = _mainScreenState.asStateFlow()

    private val _uiChannel = Channel<UiEffect>()
    val uiEffect = _uiChannel.consumeAsFlow()

    init {
        fetchMainScreenDataSource()
    }

    private fun fetchMainScreenDataSource() {
        viewModelScope.launch {
            _mainScreenState.value = _mainScreenState.value.copy(isLoading = true)

            when (val response = fetchMainScreenItemsUseCase()) {
                is Resource.Success -> {
                    _mainScreenState.value = _mainScreenState.value.copy(
                        isLoading = false,
                        homeStoreInfo = response.data?.homeStore ?: emptyList(),
                        bestSellers = response.data?.bestSeller ?: emptyList(),
                        numberOfItemsInTheCart = response.data?.cartInfo
                    )
                }
                is Resource.Error -> {
                    _mainScreenState.value = _mainScreenState.value.copy(isLoading = false)
                    showSnackbar(message = response.error ?: "")
                }
                else -> Unit
            }
        }
    }

    private suspend fun showSnackbar(message: String) {
        _uiChannel.send(UiEffect.ShowSnackbar(message))
    }

    fun filterButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToFilterDialogScreen)
    }

    fun cartButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToCartScreen)
    }

    fun productClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToDetailsScreen)
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        object NavigateToFilterDialogScreen : UiEffect()
        object NavigateToCartScreen : UiEffect()
        object NavigateToDetailsScreen : UiEffect()
    }
}