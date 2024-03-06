package com.echelon.upickup.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.echelon.upickup.model.SignUp
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.repository.SignUpRepository
import com.echelon.upickup.uistate.SignUpUIState
import com.echelon.upickup.uistate.SignUpUIState2
import com.echelon.upickup.uistate.SignUpUIState3
import com.echelon.upickup.validation.SignUpValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    private val signUpRepository = SignUpRepository()


    // first column
    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState
    fun onNameChanged(name: String) {
        val uiState = _uiState.value.copy(name = name)
        _uiState.value = uiState.updateValidationState()
    }
    fun onMiddleNameChanged(middleName: String) {
        val uiState = _uiState.value.copy(middleName = middleName)
        _uiState.value = uiState.updateValidationState()    }
    fun onLastNameChanged(lastName: String) {
        val uiState = _uiState.value.copy(lastName = lastName)
        _uiState.value = uiState.updateValidationState()
    }
    fun onGenderChanged(gender: String) {
        val uiState = _uiState.value.copy(gender = gender)
        _uiState.value = uiState.updateValidationState()
    }
    fun onAgeChanged(age: String) {
        val uiState = _uiState.value.copy(age = age)
        _uiState.value = uiState.updateValidationState()
    }
    private fun SignUpUIState.updateValidationState(): SignUpUIState {
        val isNameValid = SignUpValidation.isNameValid(name)
        val isMiddleNameValid = SignUpValidation.isNameValid(middleName)
        val isLastNameValid = SignUpValidation.isNameValid(lastName)
        val isGenderValid = SignUpValidation.isGenderValid(gender)
        val isAgeValid = SignUpValidation.isAgeValid(age)
        // update validation state based on individual fields
        return copy(
            isFormValid =
            isNameValid &&
                    isMiddleNameValid &&
                    isLastNameValid &&
                    isGenderValid &&
                    isAgeValid
        )
    }


    // second column
    private val _uiState2 = MutableStateFlow(SignUpUIState2())
    val uiState2: StateFlow<SignUpUIState2> = _uiState2

    fun onProgramChanged(program: String) {
        val uiState = _uiState2.value.copy(program = program)
        _uiState2.value = uiState.updateValidationState()
    }
    fun onDepartmentChanged(department: String) {
        val uiState = _uiState2.value.copy(department = department)
        _uiState2.value = uiState.updateValidationState()
    }

    private fun SignUpUIState2.updateValidationState(): SignUpUIState2 {
        val isProgramValid = SignUpValidation.isProgramValid(program)
        val isDepartmentValid = SignUpValidation.isDepartmentValid(department)
        // update validation state based on individual fields
        return copy(
            isFormValid =
            isProgramValid &&
                    isDepartmentValid
        )
    }


    // third column
    private val _uiState3 = MutableStateFlow(SignUpUIState3())
    val uiState3: StateFlow<SignUpUIState3> = _uiState3

    fun onEmailChanged(email: String) {
        val uiState = _uiState3.value.copy(email = email)
        _uiState3.value = uiState.updateValidationState()
    }
    fun onStudentIdChanged(idNumber: String) {
        val uiState = _uiState3.value.copy(idNumber = idNumber)
        _uiState3.value = uiState.updateValidationState()
    }

    fun onPasswordChanged(password: String) {
        val uiState = _uiState3.value.copy(password = password)
        _uiState3.value = uiState.updateValidationState()
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        val uiState = _uiState3.value.copy(confirmPassword = confirmPassword)
        _uiState3.value = uiState.updateValidationState()
    }

    private fun SignUpUIState3.updateValidationState(): SignUpUIState3 {

        val isEmailValid = SignUpValidation.isEmailValid(email)
        val isIdNumberValid = SignUpValidation.isIdNumberValid(idNumber)
        val isPasswordValid = SignUpValidation.isPasswordValid(password)
        val isConfirmPasswordValid = SignUpValidation.isConfirmPasswordValid(password, confirmPassword)

        // update validation state based on individual fields
        return copy(
            isFormValid =
                    isEmailValid &&
                    isIdNumberValid &&
                    isPasswordValid &&
                    isConfirmPasswordValid
        )
    }


    fun signUp() {
        val signUpData = SignUp(
            name = _uiState.value.name,
            middleName = _uiState.value.middleName,
            lastName = _uiState.value.lastName,
            gender = _uiState.value.gender,
            age = _uiState.value.age,
            program = _uiState2.value.program,
            department = _uiState2.value.department,
            email = _uiState3.value.email,
            idNumber = _uiState3.value.idNumber,
            password = _uiState3.value.password,
            confirmPassword = _uiState3.value.confirmPassword
        )
        viewModelScope.launch {
            try {
                // make api call to sign up
                val response = signUpRepository.signUp(signUpData)
                Log.d("SignUpResponse", "Response: ${response.message()}")
                Toast.makeText(navController.context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.SignInScreen.route)
            } catch (e: Exception) {
                Log.e("SignUpResponse", "Exception: ${e.message}")
                Toast.makeText(navController.context, "Sign Up Failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
