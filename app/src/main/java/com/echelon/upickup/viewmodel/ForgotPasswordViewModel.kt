package com.echelon.upickup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.echelon.upickup.model.ForgotPassword
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.uistate.ForgotPasswordUIState
import com.echelon.upickup.validation.ForgotPasswordValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForgotPasswordViewModel(private val navController: NavController) : ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUIState())
    val uiState: StateFlow<ForgotPasswordUIState> = _uiState

    fun onEmailChanged(email: String) {
        val isEmailValid = ForgotPasswordValidation.isEmailValid(email)
        _uiState.value = _uiState.value.copy(email = email, isEmailValid = isEmailValid)
    }

    fun resetPassword() {
        val email = ForgotPassword(_uiState.value.email)
        //reset pass logic
        //can send a reset password link to the provided email address
        navController.navigate(Screen.SignInScreen.route)
    }
}