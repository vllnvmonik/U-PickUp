package com.echelon.upickup.appscreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.ClassDetailsBox
import com.echelon.upickup.components.CustomColorTitleText
import com.echelon.upickup.components.LogoutButton
import com.echelon.upickup.components.LogoutDialog
import com.echelon.upickup.components.StaticProfile
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.sharedprefs.AuthManager
import com.echelon.upickup.sharedprefs.StudentDetailsManager
import com.echelon.upickup.sharedprefs.TokenManager
import com.echelon.upickup.viewmodel.LogoutViewModel
import com.echelon.upickup.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel) {
    val studentDetails = StudentDetailsManager.getStudentDetails()
    Log.d("ProfileScreen", "Student details: $studentDetails")
    val logoutViewModel = LogoutViewModel()

    val showDialog = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), // for scrollable screen
        color = colorResource(id = R.color.whitee)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
//                ProfileImage()
                StaticProfile()
                Spacer(modifier = Modifier.height(20.dp))
                studentDetails.let { details ->
                    if (details != null) {
                        CustomColorTitleText(
                            text = details.firstName + " " + details.lastName,
                            color = R.color.dark_green,
                            22,
                            fontWeight = FontWeight.Medium
                        )
                        CustomColorTitleText(
                            text = details.program,
                            color = R.color.dark_green,
                            16,
                            FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    studentDetails.let { details ->
                        if (details != null) {
                            ClassDetailsBox(
                                email = details.email,
                                studentId = details.studentId,
                                gender = details.gender,
                                age = details.age,
                                program = details.program,
                                department = details.department,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    LogoutButton(text = stringResource(R.string.logout)) {
                        showDialog.value = true
                    }
                }
            }
        }
    }
    if (showDialog.value) {
        LogoutDialog(
            onConfirm = {
                showDialog.value = false
                val token = AuthManager.getAuthToken()
                if (token != null) {
                    Log.d("tokeennnnnn", token)
                }
                if (!token.isNullOrBlank()) {
                    logoutViewModel.logout(token)
                    AuthManager.clearAuthToken()
                    StudentDetailsManager.clearStudentDetails()
                    navController.navigate(Screen.SignInScreen.route) {
                        popUpTo(Screen.SignInScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    Log.e("ProfileScreen", "Token is null or blank")
                }
            },
            onDismiss = {
                showDialog.value = false
            }
        )
    }
}

