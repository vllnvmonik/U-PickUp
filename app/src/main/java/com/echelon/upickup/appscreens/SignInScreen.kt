package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.echelon.upickup.validation.SignInValidation
import com.echelon.upickup.viewmodel.SignInViewModel

@Composable
fun SignInScreen(navController: NavHostController, viewModel: SignInViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            LogoImage()
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = stringResource(R.string.hey_there))
            Spacer(modifier = Modifier.height(40.dp))

            EditText(
                value = uiState.idNumber,
                onValueChange = viewModel::onIdNumberChanged,
                title = stringResource(R.string.id_number),
                keyboardType = KeyboardType.Number,
                isError = uiState.idNumber.isNotBlank() && !SignInValidation.isIdNumberValid(uiState.idNumber),
                errorMessage = "Must be in XX-XXXX-XXXXX format"
            )

            Spacer(modifier = Modifier.height(5.dp))

            EditText(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChanged,
                title = stringResource(R.string.password),
                keyboardType = KeyboardType.Password,
                isError = uiState.password.isNotBlank() && !SignInValidation.isPasswordValid(uiState.password)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth().padding(start = 60.dp)
            ) {
                ClickableNavigationText(
                    normalText = "",
                    clickableText = "Forgot Password?",
                    navigateTo = Screen.ForgotPasswordScreen.route,
                    navController = navController
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            RoundedButton(
                text = stringResource(R.string.sign_in),
                onClick = { viewModel.signIn() },
                enabled = uiState.isFormValid
            )

            Spacer(modifier = Modifier.height(120.dp))

            ClickableNavigationText(
                normalText = "Don't have an account?",
                clickableText = "SIGN UP!",
                navigateTo = Screen.SignUpScreen.route,
                navController = navController
            )
        }
    }
}