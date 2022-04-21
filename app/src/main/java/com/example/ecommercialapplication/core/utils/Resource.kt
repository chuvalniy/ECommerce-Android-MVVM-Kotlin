package com.example.ecommercialapplication.core.utils

sealed class Resource<T>(val data: T? = null, val error: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(data: T? = null, error: String?): Resource<T>(data, error)
}
