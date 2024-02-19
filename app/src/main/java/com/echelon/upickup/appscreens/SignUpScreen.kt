package com.echelon.upickup.appscreens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import com.echelon.upickup.components.ClickableNavigationText
import com.echelon.upickup.components.EditText
import com.echelon.upickup.components.LogoImage
import com.echelon.upickup.components.RoundedButton
import com.echelon.upickup.components.TitleText
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.validation.SignUpValidation
import com.echelon.upickup.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(navController: NavHostController, viewModel: SignUpViewModel){
    val uiState by viewModel.uiState.collectAsState()

    Log.d("Nav", "sign up")
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            LogoImage()
            Spacer(modifier = Modifier.height(10.dp))
            TitleText(text = stringResource(R.string.create_new_account))
            Spacer(modifier = Modifier.height(30.dp))
            EditText(
                value = uiState.name,
                onValueChange = viewModel::onNameChanged,
                title = stringResource(id = R.string.name),
                keyboardType = KeyboardType.Text,
                isError = uiState.name.isNotBlank() && !SignUpValidation.isNameValid(uiState.name),
            )
            Spacer(modifier = Modifier.height(5.dp))
            EditText(
                value = uiState.idNumber,
                onValueChange = viewModel::onIdNumberChanged,
                title = stringResource(id = R.string.id_number),
                keyboardType = KeyboardType.Number,
                isError = uiState.idNumber.isNotBlank() && !SignUpValidation.isNameValid(uiState.idNumber),
                errorMessage = "Must be in XX-XXXX-XXXXX format"
            )
            Spacer(modifier = Modifier.height(5.dp))
            EditText(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChanged,
                title = stringResource(id = R.string.password),
                keyboardType = KeyboardType.Text,
                isError = uiState.password.isNotBlank() && !SignUpValidation.isNameValid(uiState.password)
            )
            Spacer(modifier = Modifier.height(5.dp))
            EditText(
                value = uiState.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChanged,
                title = stringResource(id = R.string.confirm_password),
                keyboardType = KeyboardType.Text,
                isError = uiState.confirmPassword.isNotBlank() && !SignUpValidation.isNameValid(uiState.confirmPassword)
            )
            Spacer(modifier = Modifier.height(40.dp))
            RoundedButton(
                text = stringResource(R.string.sign_up),
                onClick = { viewModel.signUp() },
                enabled = uiState.isFormValid
            )
            Spacer(modifier = Modifier.height(90.dp))

            ClickableNavigationText(
                normalText = "Already have an account?",
                clickableText = "SIGN IN!",
                navigateTo = Screen.SignInScreen.route,
                navController = navController
            )
        }
    }
}