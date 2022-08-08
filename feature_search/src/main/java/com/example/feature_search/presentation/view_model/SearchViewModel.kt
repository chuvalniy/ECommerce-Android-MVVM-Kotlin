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
    val _uiEffect get() = _uiChannel.receiveAsFlow()

    init {
        searchData()
    }

    private fun searchData() = viewModelScope.launch {
        searchDataUseCase().onEach { result ->
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

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        object NavigateToUserProfile : UiEffect()
        object NavigateBack : UiEffect()
    }
}