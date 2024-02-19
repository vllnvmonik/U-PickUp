package com.echelon.upickup.validation

object ForgotPasswordValidation {
    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@phinmaed\\.com")
        return emailRegex.matches(email)
    }
}