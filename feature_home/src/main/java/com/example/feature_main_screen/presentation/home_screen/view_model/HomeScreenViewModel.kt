package com.example.feature_main_screen.presentation.home_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.model.DomainDataSource
import com.example.feature_main_screen.domain.use_case.FetchDataUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val fetchDataUseCase: FetchDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiChannel = Channel<UiEffect>()
    val uiEffect get() = _uiChannel.receiveAsFlow()

    init {
        fetchDomainDataSource()
    }

    private fun fetchDomainDataSource() = viewModelScope.launch {
        fetchDataUseCase.execute().onEach { result ->
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


    private suspend fun processErrorState(response: Resource<DomainDataSource>) {
        _uiState.value = _uiState.value.copy(isLoading = false)
        showSnackbar(message = response.error ?: "")
    }

    private fun processSuccessState(response: Resource<DomainDataSource>) {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            hotSales = response.data?.hotSales ?: emptyList(),
            bestSellers = response.data?.bestSellers ?: emptyList(),
        )
    }

    private suspend fun showSnackbar(message: String) {
        _uiChannel.send(UiEffect.ShowSnackbar(message))
    }

    fun categorySelected(selectedCategory: Int) {
        _uiState.value = _uiState.value.copy(
            currentlySelectedCategory = selectedCategory
        )
    }

    fun filterButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToFilterDialogScreen)
    }

    fun productClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToDetailsScreen)
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        object NavigateToFilterDialogScreen : UiEffect()
        object NavigateToDetailsScreen : UiEffect()
    }
}