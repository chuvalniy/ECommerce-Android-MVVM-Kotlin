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
        query: String = _uiState.value.query,
        brand: String = _uiState.value.selectedBrand,
        priceLeft: Int = _uiState.value.priceLeft,
        priceRight: Int = _uiState.value.priceRight,
        categoryType: CategoryType = _uiState.value.selectedCategory
    ) = viewModelScope.launch {
        searchDataUseCase(
            query,
            brand,
            priceLeft,
            priceRight,
            categoryType.categoryName
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        data = result.data ?: emptyList(),
                        isLoading = false
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

    private fun showSnackbar(message: String) = viewModelScope.launch {
        _uiChannel.send(UiEffect.ShowSnackbar(message))
    }

    fun queryTextChanged(query: String) {
        _uiState.value = _uiState.value.copy(query = query)

        viewModelScope.launch { searchData() }
    }

    fun brandSelected(brand: String) {
        _uiState.value = _uiState.value.copy(selectedBrand = brand)

        viewModelScope.launch { searchData() }
    }

    fun priceSelected(priceLeft: Float, priceRight: Float) {
        _uiState.value = _uiState.value.copy(
            priceLeft = priceLeft.toInt(),
            priceRight = priceRight.toInt()
        )
    }

    fun productClicked(id: String) = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToDetail(id))
    }

    fun backButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateBack)
    }

    fun filterButtonClicked() = viewModelScope.launch {
        _uiChannel.send(UiEffect.NavigateToFilters)
    }

    fun applyFiltersButtonClicked() = viewModelScope.launch {
        searchData()
        _uiChannel.send(UiEffect.NavigateBack)
    }

    fun categorySelected(category: CategoryType) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun resetFiltersButtonClicked() {
        _uiState.value = _uiState.value.copy(
            priceLeft = 0,
            priceRight = 6000
        )

        viewModelScope.launch {
            _uiChannel.send(UiEffect.NavigateBack)
            searchData()
        }
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        data class NavigateToDetail(val id: String) : UiEffect()
        object NavigateToFilters : UiEffect()
        object NavigateBack : UiEffect()
    }
}