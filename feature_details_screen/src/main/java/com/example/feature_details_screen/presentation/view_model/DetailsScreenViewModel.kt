package com.example.feature_details_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_details_screen.domain.repository.DetailsScreenRepository
import com.example.feature_details_screen.presentation.utils.DetailScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val repository: DetailsScreenRepository
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<DetailScreenEvent>(DetailScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        fetchProductDetails()
    }

    private fun fetchProductDetails() {
        viewModelScope.launch {
            _uiEvent.value = DetailScreenEvent.Loading
            when (val response = repository.fetchProductDetails()) {
                is Resource.Success -> {
                    response.data?.let { details ->
                        _uiEvent.value = DetailScreenEvent.Success(data = details)
                    }
                }
                is Resource.Error -> {
                    _uiEvent.value = DetailScreenEvent.Failure(error = response.error ?: "Error") // TODO: create const
                }
            }
        }
    }
}