package com.echelon.upickup.uistate

data class SignUpUIState(
    val name: String = "",
    val idNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isFormValid: Boolean = false
)
