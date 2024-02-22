package com.echelon.upickup.appscreens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.EditText
import com.echelon.upickup.components.LogoImage
import com.echelon.upickup.components.RoundedButton
import com.echelon.upickup.components.TitleText
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.sharedprefs.SignUpDataManager
import com.echelon.upickup.uistate.SignUpUIState
import com.echelon.upickup.validation.SignUpValidation
import com.echelon.upickup.viewmodel.SignUpViewModel

@Composable
fun SignUpScreenThree(navController:NavHostController, viewModel: SignUpViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.initializeForm()
        viewModel.loadSavedData()
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
                value = uiState.email,
                onValueChange = viewModel::onEmailChanged,
                title = stringResource(R.string.email),
                keyboardType = KeyboardType.Text,
                isError = uiState.email.isNotBlank()
            )
            Spacer(modifier = Modifier.height(10.dp))
            EditText(
                value = uiState.idNumber,
                onValueChange = viewModel::onStudentIdChanged,
                title = stringResource(id = R.string.id_number),
                keyboardType = KeyboardType.Number,
                isError = uiState.idNumber.isNotBlank() && !SignUpValidation.isIdNumberValid(uiState.idNumber),
                errorMessage = stringResource(R.string.must_be_in_xx_xxxx_xxxxx_format)
            )
            Spacer(modifier = Modifier.height(10.dp))
            EditText(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChanged,
                title = stringResource(id = R.string.password),
                keyboardType = KeyboardType.Password,
                isError = uiState.password.isNotBlank() && !SignUpValidation.isNameValid(uiState.password)
            )
            Spacer(modifier = Modifier.height(10.dp))

            EditText(
                value = uiState.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChanged,
                title = stringResource(id = R.string.confirm_password),
                keyboardType = KeyboardType.Password,
                isError = uiState.confirmPassword.isNotBlank() && !SignUpValidation.isNameValid(uiState.confirmPassword)
            )
            Spacer(modifier = Modifier.height(40.dp))

            RoundedButton(
                text = stringResource(R.string.sign_up),
                onClick = {
                    viewModel.signUp()
                    SignUpDataManager.clearSignUpData(navController.context)
                    },
                enabled = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            RoundedButton(
                text = stringResource(R.string.go_back),
                onClick = {
                    navController.popBackStack()
                    SignUpDataManager.loadSignUpData(navController.context)
                },
                enabled = true
            )
        }
    }
}
