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
) {
    val isFormValid: Boolean
        get() = isNameValid(name) &&
                isNameValid(middleName) &&
                isNameValid(lastName) &&
                isGenderValid(gender) &&
                isAgeValid(age)
}