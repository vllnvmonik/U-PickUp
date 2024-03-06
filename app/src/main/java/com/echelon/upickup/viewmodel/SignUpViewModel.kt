package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.echelon.upickup.model.SignUp
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.sharedprefs.SignUpDataManager
import com.echelon.upickup.uistate.SignUpUI2State
import com.echelon.upickup.uistate.SignUpUI3State
import com.echelon.upickup.uistate.SignUpUIState
import com.echelon.upickup.validation.SignUpValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel(private val navController: NavController) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState

    private val _uiState2 = MutableStateFlow(SignUpUI2State())
    val uiState2: StateFlow<SignUpUI2State> = _uiState2

    private val _uiState3 = MutableStateFlow(SignUpUI3State())
    val uiState3: StateFlow<SignUpUI3State> = _uiState3

    private var isFormInitialized: Boolean = false

    fun initializeForm() {
        Log.d("SignUpViewModel", "Initializing form")
        if (!isFormInitialized) {
            val savedData = loadSavedData()
            Log.d("SignUpViewModel", "Loaded data: $savedData")

            _uiState.value = savedData.first
            _uiState2.value = savedData.second
            _uiState3.value = savedData.third

            isFormInitialized = true
        }
    }

    fun loadSavedData(): Triple<SignUpUIState, SignUpUI2State, SignUpUI3State> {
        Log.d("SignUpViewModel", "Loading saved data")
        return SignUpDataManager.loadSignUpData(navController.context)
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
        _uiState2.value = _uiState2.value.copy(program = program)

    }
    fun onDepartmentChanged(department: String) {
        _uiState2.value = _uiState2.value.copy(department = department)
    }
    fun onGenderChanged(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }
    fun onAgeChanged(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }
    fun onEmailChanged(email: String) {
        _uiState3.value = _uiState3.value.copy(email = email)
    }
    fun onStudentIdChanged(idNumber: String) {
        _uiState3.value = _uiState3.value.copy(idNumber = idNumber)
    }

    fun onPasswordChanged(password: String) {
        _uiState3.value = _uiState3.value.copy(password = password)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _uiState3.value = _uiState3.value.copy(confirmPassword = confirmPassword)
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
        SignUpDataManager.saveSignUpUIState(navController.context, _uiState.value)
        SignUpDataManager.saveSignUpUI2State(navController.context, _uiState2.value)
        SignUpDataManager.saveSignUpUI3State(navController.context, _uiState3.value)

        Log.d("SignUpViewModel", "Sign up data saved: $signUpData")
        navController.navigate(Screen.SignInScreen.route)
        resetFormState()
    }
    fun updateUIState(savedData: Triple<SignUpUIState, SignUpUI2State, SignUpUI3State>) {
        _uiState.value = savedData.first
        _uiState2.value = savedData.second
        _uiState3.value = savedData.third
    }

    private fun resetFormState() {
        _uiState.value = SignUpUIState()
        _uiState2.value = SignUpUI2State()
        _uiState3.value = SignUpUI3State()
        isFormInitialized = false
    }
}