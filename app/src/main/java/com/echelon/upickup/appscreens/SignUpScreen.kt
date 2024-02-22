package com.echelon.upickup.appscreens

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
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
import com.echelon.upickup.sharedprefs.SignUpDataManager
import com.echelon.upickup.uistate.SignUpUIState
import com.echelon.upickup.validation.SignUpValidation
import com.echelon.upickup.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(navController: NavHostController, viewModel: SignUpViewModel){
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = true) {
        Log.d("SignUpScreen", "Initializing form")
        viewModel.initializeForm()
        viewModel.loadSavedData()
    }
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            LogoImage()
            Spacer(modifier = Modifier.height(10.dp))
            TitleText(text = stringResource(R.string.create_new_account))
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                EditText(
                    value = uiState.name,
                    onValueChange = viewModel::onNameChanged,
                    title = stringResource(id = R.string.name),
                    keyboardType = KeyboardType.Text,
                    isError = uiState.name.isNotBlank() && !SignUpValidation.isNameValid(uiState.name),
                )
                Spacer(modifier = Modifier.height(5.dp))
                EditText(
                    value = uiState.middleName,
                    onValueChange = viewModel::onMiddleNameChanged,
                    title = stringResource(R.string.middle_name),
                    keyboardType = KeyboardType.Text,
                    isError = uiState.middleName.isNotBlank() && !SignUpValidation.isNameValid(uiState.middleName),
                    errorMessage = ""
                )
                Spacer(modifier = Modifier.height(5.dp))

                EditText(
                    value = uiState.lastName,
                    onValueChange = viewModel::onLastNameChanged,
                    title = stringResource(R.string.last_name),
                    keyboardType = KeyboardType.Text,
                    isError = uiState.lastName.isNotBlank() && !SignUpValidation.isNameValid(uiState.lastName),
                    errorMessage = ""
                )
                Spacer(modifier = Modifier.height(5.dp))
                EditText(
                    value = uiState.gender,
                    onValueChange = viewModel::onGenderChanged,
                    title = stringResource(R.string.gender),
                    keyboardType = KeyboardType.Text,
                    isError = uiState.gender.isNotBlank() && !SignUpValidation.isNameValid(uiState.gender),
                    errorMessage = ""
                )
                Spacer(modifier = Modifier.height(5.dp))
                EditText(
                    value = uiState.age,
                    onValueChange = viewModel::onAgeChanged,
                    title = stringResource(R.string.age),
                    keyboardType = KeyboardType.Text,
                    isError = uiState.age.isNotBlank() && !SignUpValidation.isNameValid(uiState.age),
                )

                Spacer(modifier = Modifier.height(30.dp))

                RoundedButton(
                    text = stringResource(R.string.proceed),
                    onClick = {
                        navController.navigate(Screen.SignUpScreenTwo.route)
                        SignUpDataManager.saveSignUpData(navController.context, uiState)
                    },
                    enabled = true
                )
                Spacer(modifier = Modifier.height(90.dp))
                ClickableNavigationText(
                    normalText = stringResource(R.string.already_have_an_account),
                    clickableText = stringResource(R.string.sign_in),
                    navigateTo = Screen.SignInScreen.route,
                    navController = navController
                )
            }
        }
    }
}