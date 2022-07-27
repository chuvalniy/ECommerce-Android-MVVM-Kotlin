package com.example.core.utils

sealed class Resource<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(data: T? = null, error: String?): Resource<T>(data, error)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}
