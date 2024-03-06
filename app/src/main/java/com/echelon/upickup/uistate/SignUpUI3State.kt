package com.echelon.upickup.uistate

import com.echelon.upickup.validation.SignUpValidation.isConfirmPasswordValid
import com.echelon.upickup.validation.SignUpValidation.isEmailValid
import com.echelon.upickup.validation.SignUpValidation.isIdNumberValid
import com.echelon.upickup.validation.SignUpValidation.isPasswordValid

data class SignUpUI3State(
    val email: String = "",
    val idNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
) {
    val isFormValid: Boolean
        get() = isEmailValid(email) &&
                isIdNumberValid(idNumber) &&
                isPasswordValid(password) &&
                isConfirmPasswordValid(password, confirmPassword)
}
