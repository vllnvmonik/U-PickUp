package com.echelon.upickup.uistate

data class SignInUIState(
    val idNumber: String = "",
    val password: String = "",
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)