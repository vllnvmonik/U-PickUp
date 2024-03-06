package com.echelon.upickup.viewmodel
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.echelon.upickup.model.SignIn
import com.echelon.upickup.model.StudentDetails
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.repository.SignInRepository
import com.echelon.upickup.sharedprefs.AuthManager
import com.echelon.upickup.sharedprefs.StudentDetailsManager
import com.echelon.upickup.uistate.SignInUIState
import com.echelon.upickup.validation.SignInValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val navController: NavController) : ViewModel() {
    private val signInRepository = SignInRepository()
    private val _uiState = MutableStateFlow(SignInUIState())
    val uiState: StateFlow<SignInUIState> = _uiState
    private val profileViewModel = ProfileViewModel()
    fun onIdNumberChanged(idNumber: String) {
        val uiState = _uiState.value.copy(idNumber = idNumber)
        _uiState.value = uiState.updateValidationState()
    }

    fun onPasswordChanged(password: String) {
        val uiState = _uiState.value.copy(password = password)
        _uiState.value = uiState.updateValidationState()
    }

    private fun SignInUIState.updateValidationState(): SignInUIState {
        val isIdNumberValid = SignInValidation.isIdNumberValid(idNumber)
        val isPasswordValid = SignInValidation.isPasswordValid(password)
        return copy(isFormValid = isIdNumberValid && isPasswordValid)
    }

    fun signIn() {
        val credentials = SignIn(_uiState.value.idNumber, _uiState.value.password)
        viewModelScope.launch {
            val response = signInRepository.signIn(credentials)

            if (response.isSuccessful) {
                val signInResponse = response.body()

                if (signInResponse != null) {
                    val token = signInResponse.token
                    val studentData = signInResponse.data
                    AuthManager.saveAuthToken(token)

                    val id = studentData.id
                    val studentId = studentData.student_id
                    val email = studentData.email_ad
                    val firstName = studentData.first_name
                    val middleName = studentData.middle_name
                    val lastName = studentData.last_name
                    val age = studentData.age
                    val gender = studentData.gender
                    val department = studentData.department
                    val program = studentData.program

                    val studentDetails = StudentDetails(
                        id.toString(),
                        studentId,
                        email,
                        firstName,
                        middleName,
                        lastName,
                        age,
                        gender,
                        department,
                        program
                    )
                    StudentDetailsManager.saveStudentDetails(studentDetails)

                    Log.d(
                        "SignInViewModel",
                        "Sign-in successful. Token: $token, User ID: $id, Student ID: $studentId, Email: $email, First Name: $firstName, Middle Name: $middleName, Last Name: $lastName, Age: $age, Gender: $gender, Department: $department, Program: $program"
                    )
                    if (AuthManager.isLoggedIn()) {
                        profileViewModel.getStudentDetails(id)
                        navController.navigate(Screen.DashboardScreen.route)
                    } else {
                        Toast.makeText(
                            navController.context,
                            "Sign-in failed. Please try again.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        navController.context,
                        "Sign-in failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    navController.context,
                    "Sign-in failed. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}