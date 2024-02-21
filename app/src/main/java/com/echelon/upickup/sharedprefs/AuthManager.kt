package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences

object AuthManager {
    private const val PREF_NAME = "AuthPrefs"

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveAuthToken(token: String) {
        TokenManager.saveToken(token)
    }

    fun getAuthToken(): String? {
        return TokenManager.getToken()
    }

    fun clearAuthToken() {
        TokenManager.clearToken()
    }
    fun isLoggedIn(): Boolean {
        val token = getAuthToken()
        return token != null && !token.isEmpty()
    }
}