package com.example.feature_login.domain

import com.example.core.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun signUp(email: String,) : Flow<Resource<AuthResult>>

}