package com.example.feature_search.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_search.domain.use_case.SearchDataUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchDataUseCase: SearchDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchState())
    val uiState get() = _uiState.asStateFlow()

    private val _uiChannel = Channel<UiEffect>()
    val uiEffect get() = _uiChannel.receiveAsFlow()

    init {
        searchData()
    }

    private fun searchData(
        query: String = _uiState.value.searchQuery,
        brand: String = _uiState.value.brand,
        price: PriceFilter = _uiState.value.priceFilter
    ) = viewModelScope.launch {
        searchDataUseCase(query, brand, price).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        data = result.data ?: emptyList(),
                    )
                }
                is Resource.Error -> {
                    showSnackbar(message = result.error ?: "")
                }
                is Resource.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                }
            }
        }.launchIn(this)
    }

    private fun showSnackbar(message: String) = viewModelScope.launch {
        _uiChannel.send(UiEffect.ShowSnackbar(message))
    }

    fun queryTextChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)

        searchData()
    }

    fun tabSelected(brand: String) {
        _uiState.value = _uiState.value.copy(brand = brand)

        searchData()
    }

    fun priceSelected(priceFilter: PriceFilter) {
        _uiState.value = _uiState.value.copy(priceFilter = priceFilter)

        searchData()
    }


    fun itemClicked(id: String) = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToDetail(id))
    }

    fun filterButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToFilter)
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        data class NavigateToDetail(val id: String) : UiEffect()
        object NavigateToFilter : UiEffect()
        object NavigateBack : UiEffect()
    }
}