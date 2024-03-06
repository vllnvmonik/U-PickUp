package com.echelon.upickup.uistate

import com.echelon.upickup.validation.SignUpValidation.isDepartmentValid
import com.echelon.upickup.validation.SignUpValidation.isProgramValid

data class SignUpUI2State(
    val program: String = "",
    val department: String = "",
) {
    val isFormValid: Boolean
        get() = isProgramValid(program) &&
                isDepartmentValid(department)
}
