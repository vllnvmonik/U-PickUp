package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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

@Composable
fun SignInScreen(navController: NavHostController){
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        Column (modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(100.dp))
            LogoImage()
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = stringResource(R.string.hey_there))
            Spacer(modifier = Modifier.height(40.dp))
            EditText(title = stringResource(id = R.string.e_mail), KeyboardType.Text)
            Spacer(modifier = Modifier.height(5.dp))
            EditText(title = stringResource(R.string.password), KeyboardType.Text)
            Spacer(modifier = Modifier.height(5.dp))
            EditText(stringResource(R.string.id_number), KeyboardType.Number)
            Spacer(modifier = Modifier.height(50.dp))
            RoundedButton(
                text = stringResource(R.string.sign_in)
            ) {
                navController.navigate("dashboard")
            }
            Spacer(modifier = Modifier.height(120.dp))
            ClickableNavigationText(
                normalText = "Don't have an account?",
                clickableText = "SIGN UP!",
                navigateTo = "signup",
                navController = navController
            )
        }
    }
}