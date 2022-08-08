package com.example.data_user_session.data

import android.content.Context

private const val SHARED_PREFS_NAME = "user_session"
private const val KEY_USER_ID = "user_id"
private const val DEFAULT_VALUE = ""

class UserSessionStorageImpl(
    context: Context
): UserSessionStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveSessionId(userId: String) {
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
    }

    override fun getUserSessionId(): String {
        return sharedPreferences.getString(KEY_USER_ID, DEFAULT_VALUE) ?: DEFAULT_VALUE
    }
}