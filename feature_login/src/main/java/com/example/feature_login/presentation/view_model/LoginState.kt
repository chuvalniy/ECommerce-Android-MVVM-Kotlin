package com.example.feature_login.presentation.view_model

data class LoginState(
    val firstName: String = "",
    val secondName: String = "",
    val email: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
    val acceptedTerms: Boolean = false
)