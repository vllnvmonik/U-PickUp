package com.echelon.upickup.validation

object SignInValidation {
    private const val MIN_ID_NUMBER_LENGTH = 12
    private const val MIN_PASSWORD_LENGTH = 6

    fun isIdNumberValid(idNumber: String): Boolean {
        if (idNumber.length < MIN_ID_NUMBER_LENGTH) {
            return false
        }
        val idNumberRegex = Regex("\\d{2}-\\d{4}-\\d{5,6}")
        return idNumberRegex.matches(idNumber)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty() && password.length >= MIN_PASSWORD_LENGTH
    }
}

