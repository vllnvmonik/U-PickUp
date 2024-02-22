package com.echelon.upickup.uistate

data class SignUpUIState(
    val name: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val age: String = "",
    val program: String = "",
    val department: String = "",
    val email: String = "",
    val idNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isFormValid: Boolean = false
)
