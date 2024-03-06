package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.echelon.upickup.components.EditTextPassword
import com.echelon.upickup.components.LogoImage
import com.echelon.upickup.components.RoundedButton
import com.echelon.upickup.components.TitleText
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.validation.SignUpValidation
import com.echelon.upickup.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(navController: NavHostController, viewModel: SignUpViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val uiState2 by viewModel.uiState2.collectAsState()
    val uiState3 by viewModel.uiState3.collectAsState()

    var showFirstColumn by remember { mutableStateOf(true) }
    var showSecondColumn by remember { mutableStateOf(false) }
    var showThirdColumn by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_color),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Spacer(modifier = Modifier.height(20.dp))
            LogoImage()
//            Spacer(modifier = Modifier.height(10.dp))
            TitleText(text = stringResource(R.string.create_new_account))
            Spacer(modifier = Modifier.height(20.dp))

            // first Column
            if (showFirstColumn) {
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
                        isError = uiState.gender.isNotBlank() && !SignUpValidation.isGenderValid(uiState.gender),
                        errorMessage = ""
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    EditText(
                        value = uiState.age,
                        onValueChange = viewModel::onAgeChanged,
                        title = stringResource(R.string.age),
                        keyboardType = KeyboardType.Text,
                        isError = uiState.age.isNotBlank() && !SignUpValidation.isAgeValid(uiState.age),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    RoundedButton(
                        text = "Proceed",
                        onClick = {
                            showFirstColumn = false
                            showSecondColumn = true
                        },
                        validation = { uiState.isFormValid },
                        errorText = "Please fill in all required fields"
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    ClickableNavigationText(
                        normalText = stringResource(R.string.already_have_an_account),
                        clickableText = stringResource(R.string.sign_in),
                        navigateTo = Screen.SignInScreen.route,
                        navController = navController
                    )
                }
            }

            // second Column
            if (showSecondColumn) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    EditText(
                        value = uiState2.program,
                        onValueChange = viewModel::onProgramChanged,
                        title = stringResource(R.string.program),
                        keyboardType = KeyboardType.Text,
                        isError = uiState2.program.isNotBlank() && !SignUpValidation.isProgramValid(uiState2.program),
                        errorMessage = "Invalid Program"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    EditText(
                        value = uiState2.department,
                        onValueChange = viewModel::onDepartmentChanged,
                        title = "Department",
                        keyboardType = KeyboardType.Text,
                        isError = uiState2.department.isNotBlank() && !SignUpValidation.isDepartmentValid(uiState2.department),
                        errorMessage = "Invalid Department"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    RoundedButton(
                        text = "Proceed",
                        onClick = {
                            // check if the form is valid before proceeding
                            if (uiState2.isFormValid) {
                                showSecondColumn = false
                                showThirdColumn = true
                            }
                        },
                        validation = { uiState2.isFormValid },
                        errorText = "Please fill in all required fields"
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    RoundedButton(
                        text = "Go back",
                        onClick = {
                            showFirstColumn = true
                            showSecondColumn = false
                        },
                        validation = { true },
                        errorText = ""
                    )
                }
            }

            // third Column
            if (showThirdColumn) {
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
                        isError = uiState3.idNumber.isNotBlank() && !SignUpValidation.isIdNumberValid(uiState3.idNumber),
                        errorMessage = stringResource(R.string.must_be_in_xx_xxxx_xxxxx_format)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    EditTextPassword(
                        value = uiState3.password,
                        onValueChange = viewModel::onPasswordChanged,
                        title = stringResource(id = R.string.password),
                        isError = uiState3.password.isNotBlank() && !SignUpValidation.isPasswordValid(uiState3.password)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    EditTextPassword(
                        value = uiState3.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChanged,
                        title = stringResource(id = R.string.confirm_password),
                        isError = uiState3.confirmPassword.isNotBlank() && !SignUpValidation.isConfirmPasswordValid(uiState3.password, uiState3.confirmPassword)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    RoundedButton(
                        text = stringResource(R.string.sign_up),
                        onClick = {
                            // check if the form is valid before signing up
                            if ( uiState.isFormValid && uiState2.isFormValid && uiState3.isFormValid) {
                                viewModel.signUp()
                                navController.navigate(Screen.SignInScreen.route)
                            }
                        },
                        // validate the form before enabling the button
                        validation = { uiState.isFormValid && uiState2.isFormValid && uiState3.isFormValid},
                        errorText = "Please fill in all required fields"
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    RoundedButton(
                        text = "Go back",
                        onClick = {
                            showSecondColumn = true
                            showThirdColumn = false
                        },
                        validation = { true },
                        errorText = ""
                    )
                }
            }
        }
    }
}
