package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.echelon.upickup.model.SignUp
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.sharedprefs.SignUpDataManager
import com.echelon.upickup.uistate.SignUpUIState
import com.echelon.upickup.validation.SignUpValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState

    private var isFormInitialized: Boolean = false

    fun initializeForm() {
        Log.d("SignUpViewModel", "Initializing form")
        if (!isFormInitialized) {
            val savedData = SignUpDataManager.loadSignUpData(navController.context)
            Log.d("SignUpViewModel", "Loaded data: $savedData")
            _uiState.value = savedData
            isFormInitialized = true
        }
    }

    fun loadSavedData() {
        Log.d("SignUpViewModel", "Initializing form")
        val savedData = SignUpDataManager.loadSignUpData(navController.context)
        Log.d("SignUpViewModel", "Loaded data: $savedData")
        _uiState.value = savedData
    }
    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }
    fun onMiddleNameChanged(middleName: String) {
        _uiState.value = _uiState.value.copy(middleName = middleName)
    }
    fun onLastNameChanged(lastName: String) {
        _uiState.value = _uiState.value.copy(lastName = lastName)

    }
    fun onProgramChanged(program: String) {
        _uiState.value = _uiState.value.copy(program = program)

    }
    fun onDepartmentChanged(department: String) {
        _uiState.value = _uiState.value.copy(department = department)
    }
    fun onGenderChanged(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }
    fun onAgeChanged(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }
    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }
    fun onStudentIdChanged(idNumber: String) {
        _uiState.value = _uiState.value.copy(idNumber = idNumber)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
    }

    fun signUp() {
        val signUpData = SignUp(
            name = _uiState.value.name,
            middleName = _uiState.value.middleName,
            lastName = _uiState.value.lastName,
            gender = _uiState.value.gender,
            age = _uiState.value.age,
            program = _uiState.value.program,
            department = _uiState.value.department,
            email = _uiState.value.email,
            idNumber = _uiState.value.idNumber,
            password = _uiState.value.password,
            confirmPassword = _uiState.value.confirmPassword
        )
        SignUpDataManager.saveSignUpData(navController.context, _uiState.value)
        Log.d("SignUpViewModel", "Sign up data saved: $signUpData")
        navController.navigate(Screen.SignInScreen.route)
        resetFormState()
    }

    private fun resetFormState() {
        _uiState.value = SignUpUIState()
        isFormInitialized = false
    }
}