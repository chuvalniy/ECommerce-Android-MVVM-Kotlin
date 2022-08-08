package com.example.data_user_session.data

interface UserSessionStorage {

    fun saveSessionId(userId: String)

    fun getUserSessionId(): String
}