package com.echelon.upickup.uistate

import com.echelon.upickup.validation.SignUpValidation.isAgeValid
import com.echelon.upickup.validation.SignUpValidation.isGenderValid
import com.echelon.upickup.validation.SignUpValidation.isNameValid

data class SignUpUIState(
    val name: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val age: String = "",
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
data class SignUpUIState2(
    val program: String = "",
    val department: String = "",
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
data class SignUpUIState3(
    val email: String = "",
    val idNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)