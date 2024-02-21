package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.repository.LogoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogoutViewModel : ViewModel() {
    private val logoutRepository = LogoutRepository()

    fun logout(token: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = logoutRepository.logout(token)
                    if (response.isSuccessful) {
                        Log.d("LogoutViewModel", "Logged out successfully")
                    } else {
                        Log.e("LogoutViewModel", "Failed to logout: ${response.errorBody()?.string()}")
                    }
                } catch (e: Exception) {
                    Log.e("LogoutViewModel", "Error occurred during logout", e)
                }
            }
        }
    }
}