package com.echelon.upickup.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.repository.ForgotPasswordRepository
import com.echelon.upickup.uistate.ForgotPasswordUIState
import com.echelon.upickup.validation.ForgotPasswordValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val navController: NavController) : ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUIState())
    val uiState: StateFlow<ForgotPasswordUIState> = _uiState
    private val forgotPasswordRepository = ForgotPasswordRepository()
    fun onEmailChanged(email: String) {
        val isEmailValid = ForgotPasswordValidation.isEmailValid(email)
        _uiState.value = _uiState.value.copy(email = email, isEmailValid = isEmailValid)
    }

    fun resetPassword() {
        val email = _uiState.value.email
        if (ForgotPasswordValidation.isEmailValid(email)) {
            viewModelScope.launch {
                val response = forgotPasswordRepository.requestPasswordReset(email)
                if (response != null) {
                    Log.d("ForgotPasswordResponse", "Response: ${response.message}")
                    Toast.makeText(
                        navController.context,
                        "Password Reset Link Sent Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e("ForgotPasswordResponse", "Failed to send email")
                    Toast.makeText(
                        navController.context,
                        "Failed to send email. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                navController.navigate(Screen.SignInScreen.route)
            }
        } else {
            Log.e("ForgotPasswordResponse", "Invalid email format")
            Toast.makeText(
                navController.context,
                "Invalid email format. Please enter a valid email address.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}