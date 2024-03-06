package com.echelon.upickup.appscreens

import android.util.Log
import androidx.compose.foundation.layout.*
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
fun SignUpScreenTwo(navController: NavHostController,viewModel: SignUpViewModel) {
    val uiState2 by viewModel.uiState2.collectAsState()

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
            TitleText(text = stringResource(R.string.let_us_know_you_more))
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                EditText(
                    value = uiState2.program,
                    onValueChange = viewModel::onProgramChanged,
                    title = stringResource(R.string.program),
                    keyboardType = KeyboardType.Text,
                    isError = uiState2.program.isNotBlank() && !SignUpValidation.isProgramValid(
                        uiState2.program
                    ),
                    errorMessage = "Invalid Program"
                )
                Spacer(modifier = Modifier.height(10.dp))
                EditText(
                    value = uiState2.department,
                    onValueChange = viewModel::onDepartmentChanged,
                    title = "Department",
                    keyboardType = KeyboardType.Text,
                    isError = uiState2.department.isNotBlank() && !SignUpValidation.isDepartmentValid(
                        uiState2.department
                    ),
                    errorMessage = "Invalid Department"
                )
                Spacer(modifier = Modifier.height(30.dp))
                RoundedButton(
                    text = stringResource(R.string.proceed),
                    onClick = {
                        navController.navigate(Screen.SignUpScreenThree.route)
                        SignUpDataManager.saveSignUpUI2State(
                            context = navController.context,
                            signUpUI2State = uiState2,
                        )
                    },
                    enabled = true,
                    validation = { uiState2.isFormValid },
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
                    validation = { true },
                    errorText = ""
                )

            }
        }
    }
}
