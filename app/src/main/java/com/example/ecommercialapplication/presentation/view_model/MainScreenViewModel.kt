package com.example.ecommercialapplication.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.ecommercialapplication.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: MainScreenRepository
) : ViewModel() {


    private val _data = MutableStateFlow(CartState())
    val data get() = _data.asStateFlow()


    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        when (val result = repository.fetchData()) {
            is Resource.Success -> processCartInfo(result.data?.itemsInTheCart)
            is Resource.Error -> Unit
            is Resource.Loading -> Unit
        }
    }

    private fun processCartInfo(itemsInTheCart: Int?) {
        if (itemsInTheCart == null || itemsInTheCart <= 0) {
            _data.value = _data.value.copy(isCartVisible = false)
        } else {
            _data.value = _data.value.copy(isCartVisible = true, currentCartItems = itemsInTheCart)
        }
    }
}