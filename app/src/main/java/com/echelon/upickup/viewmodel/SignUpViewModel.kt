package com.echelon.upickup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.echelon.upickup.model.SignUp
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.uistate.SignUpUIState
import com.echelon.upickup.validation.SignUpValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState

    fun onNameChanged(name: String) {
        val uiState = _uiState.value.copy(name = name)
        _uiState.value = uiState.updateValidationState()
    }

    fun onIdNumberChanged(idNumber: String) {
        val uiState = _uiState.value.copy(idNumber = idNumber)
        _uiState.value = uiState.updateValidationState()
    }

    fun onPasswordChanged(password: String) {
        val uiState = _uiState.value.copy(password = password)
        _uiState.value = uiState.updateValidationState()
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        val uiState = _uiState.value.copy(confirmPassword = confirmPassword)
        _uiState.value = uiState.updateValidationState()
    }

    private fun SignUpUIState.updateValidationState(): SignUpUIState {
        val isNameValid = SignUpValidation.isNameValid(name)
        val isIdNumberValid = SignUpValidation.isIdNumberValid(idNumber)
        val isPasswordValid = SignUpValidation.isPasswordValid(password)
        val isConfirmPasswordValid = SignUpValidation.isConfirmPasswordValid(password, confirmPassword)
        return copy(isFormValid = isNameValid && isIdNumberValid && isPasswordValid && isConfirmPasswordValid)
    }

    fun signUp() {
        val signUpData = SignUp(_uiState.value.name, _uiState.value.idNumber, _uiState.value.password, _uiState.value.confirmPassword)
        // navigate
        navController.navigate(Screen.SignInScreen.route)
        //show later on if sign up is successful
    }
}