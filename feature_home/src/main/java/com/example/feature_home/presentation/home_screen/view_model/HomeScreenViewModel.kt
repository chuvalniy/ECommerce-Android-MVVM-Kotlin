package com.example.feature_home.presentation.home_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_home.domain.use_case.FetchDataUseCase
import com.example.feature_home.domain.use_case.FilterBestSellerDomain
import com.example.feature_home.domain.use_case.FilterHotSalesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val fetchDataUseCase: FetchDataUseCase,
    private val filterBestSellerDomain: FilterBestSellerDomain,
    private val filterHotSalesUseCase: FilterHotSalesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiChannel = Channel<UiEffect>()
    val uiEffect get() = _uiChannel.receiveAsFlow()

    init {
        fetchData()
    }

    private fun fetchData(
        selectedCategory: Int = _uiState.value.currentlySelectedCategory
    ) = viewModelScope.launch {
        fetchDataUseCase.execute(selectedCategory).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        hotSales =  filterHotSalesUseCase.execute(result.data ?: emptyList()),
                        bestSellers = filterBestSellerDomain.execute(result.data ?: emptyList())
                    )
                }
                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    showSnackbar(message = result.error ?: "")
                }
                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                }
            }
        }.launchIn(this)
    }


    private suspend fun showSnackbar(message: String) {
        _uiChannel.send(UiEffect.ShowSnackbar(message))
    }

    fun categorySelected(selectedCategory: Int) {
        _uiState.value = _uiState.value.copy(
            currentlySelectedCategory = selectedCategory
        )
        viewModelScope.launch { fetchData() }
    }

    fun productClicked(id: String) = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToDetailsScreen(id))
    }

    fun searchClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToSearchScreen)
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        data class NavigateToDetailsScreen(val id: String) : UiEffect()
        object NavigateToSearchScreen : UiEffect()
    }
}