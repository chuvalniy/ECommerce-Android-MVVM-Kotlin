package com.example.feature_main_screen.presentation.main_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.model.DomainDataSource
import com.example.feature_main_screen.domain.use_case.FetchMainScreenDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val fetchMainScreenDataSource: FetchMainScreenDataSource
) : ViewModel() {

    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState get() = _mainScreenState.asStateFlow()

    private val _uiChannel = Channel<UiEffect>()
    val uiEffect get() = _uiChannel.receiveAsFlow()

    init {
        fetchMainScreenDataSource()
    }

    private fun fetchMainScreenDataSource() {
        viewModelScope.launch {
            _mainScreenState.value = _mainScreenState.value.copy(isLoading = true)

            when (val response = fetchMainScreenDataSource.execute()) {
                is Resource.Success -> {
                    processSuccessState(response)
                }
                is Resource.Error -> {
                    processErrorState(response)
                }
                else -> Unit
            }
        }
    }

    private suspend fun processErrorState(response: Resource<DomainDataSource>) {
        _mainScreenState.value = _mainScreenState.value.copy(isLoading = false)
        showSnackbar(message = response.error ?: "")
    }

    private fun processSuccessState(response: Resource<DomainDataSource>) {
        _mainScreenState.value = _mainScreenState.value.copy(
            isLoading = false,
            hotSales = response.data?.hotSales ?: emptyList(),
            bestSellers = response.data?.bestSellers ?: emptyList(),
            numberOfItemsInTheCart = response.data?.cartInfo ?: 0
        )
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