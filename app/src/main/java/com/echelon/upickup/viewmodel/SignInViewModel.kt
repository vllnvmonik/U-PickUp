package com.echelon.upickup.viewmodel
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.echelon.upickup.model.SignIn
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.repository.SignInRepository
import com.echelon.upickup.uistate.SignInUIState
import com.echelon.upickup.validation.SignInValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val navController: NavController) : ViewModel() {
    private val signInRepository = SignInRepository()
    private val _uiState = MutableStateFlow(SignInUIState())
    val uiState: StateFlow<SignInUIState> = _uiState


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
        // the credentials will be used for authentication
        val credentials = SignIn(_uiState.value.idNumber, _uiState.value.password)
        viewModelScope.launch {
            val response = signInRepository.signIn(credentials)
            if (response != null) {
                navController.navigate(Screen.DashboardScreen.route)
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