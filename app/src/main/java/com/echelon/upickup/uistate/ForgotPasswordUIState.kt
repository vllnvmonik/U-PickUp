package com.echelon.upickup.uistate

data class ForgotPasswordUIState(
    val email: String = "",
    val isEmailValid: Boolean = false
)