package com.example.feature_main_screen.presentation.main_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import com.example.feature_main_screen.presentation.main_screen.fragment.MainScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val repository: MainScreenRepository
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<MainScreenEvent>(MainScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        fetchMainScreenItems()
    }

    private fun fetchMainScreenItems() {
        viewModelScope.launch {
            _uiEvent.value = MainScreenEvent.Loading
            when (val response = repository.fetchMainScreenItems()) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        _uiEvent.value = MainScreenEvent.Success(data = data)
                    }
                }
                is Resource.Error -> {
                    _uiEvent.value = MainScreenEvent.Failure(
                        error = response.error ?: "Unexpected error occurred"
                    )
                }
                else -> Unit
            }
        }
    }
}