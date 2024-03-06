package com.echelon.upickup.appscreens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.EditText
import com.echelon.upickup.components.EditTextPassword
import com.echelon.upickup.components.LogoImage
import com.echelon.upickup.components.RoundedButton
import com.echelon.upickup.components.TitleText
import com.echelon.upickup.sharedprefs.SignUpDataManager
import com.echelon.upickup.validation.SignUpValidation
import com.echelon.upickup.viewmodel.SignUpViewModel

@Composable
fun SignUpScreenThree(navController:NavHostController, viewModel: SignUpViewModel) {
    val uiState3 by viewModel.uiState3.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.initializeForm()
        viewModel.loadSavedData()
    }
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            LogoImage()
            Spacer(modifier = Modifier.height(10.dp))
            TitleText(text = "FINAL TOUCHES!")
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                EditText(
                    value = uiState3.email,
                    onValueChange = viewModel::onEmailChanged,
                    title = stringResource(R.string.email),
                    keyboardType = KeyboardType.Text,
                    isError = uiState3.email.isNotBlank() && !SignUpValidation.isEmailValid(uiState3.email)
                )
                Spacer(modifier = Modifier.height(10.dp))
                EditText(
                    value = uiState3.idNumber,
                    onValueChange = viewModel::onStudentIdChanged,
                    title = stringResource(id = R.string.id_number),
                    keyboardType = KeyboardType.Number,
                    isError = uiState3.idNumber.isNotBlank() && !SignUpValidation.isIdNumberValid(
                        uiState3.idNumber
                    ),
                    errorMessage = stringResource(R.string.must_be_in_xx_xxxx_xxxxx_format)
                )
                Spacer(modifier = Modifier.height(10.dp))
                EditTextPassword(
                    value = uiState3.password,
                    onValueChange = viewModel::onPasswordChanged,
                    title = stringResource(id = R.string.password),
                    isError = uiState3.password.isNotBlank() && !SignUpValidation.isPasswordValid(
                        uiState3.password
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))

                EditTextPassword(
                    value = uiState3.confirmPassword,
                    onValueChange = viewModel::onConfirmPasswordChanged,
                    title = stringResource(id = R.string.confirm_password),
                    isError = uiState3.confirmPassword.isNotBlank() && !SignUpValidation.isConfirmPasswordValid(
                        uiState3.password,
                        uiState3.confirmPassword
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))

                RoundedButton(
                    text = stringResource(R.string.sign_up),
                    onClick = {
                        viewModel.signUp()
                        SignUpDataManager.clearSignUpData(navController.context)
                    },
                    enabled = true,
                    validation = { uiState3.isFormValid },
                    errorText = "Please fill in all required fields"
                )
                Spacer(modifier = Modifier.height(20.dp))
            RoundedButton(
                text = stringResource(R.string.go_back),
                onClick = {
                    val savedData = SignUpDataManager.loadSignUpData(navController.context)
                    viewModel.updateUIState(savedData)
                    navController.popBackStack()
                    Log.d("SignUpScreenThree", "Loaded data: $savedData")

                },
                enabled = true,
                validation = {true},
                errorText = ""
            )
            }
        }
    }
}
