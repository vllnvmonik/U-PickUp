package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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

@Composable
fun ForgotPasswordScreen(navController: NavHostController){
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
            TitleText(text = stringResource(R.string.forgot_password_uppercase))
            Spacer(modifier = Modifier.height(40.dp))
            EditText(title = stringResource(id = R.string.e_mail), KeyboardType.Text)
            Spacer(modifier = Modifier.height(50.dp))
            RoundedButton(
                text = "RESET PASSWORD"
            ) {
                navController.navigate(Screen.DashboardScreen.route)
            }
            Spacer(modifier = Modifier.height(50.dp))
            Column (

            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left_solid),
                        contentDescription = "back",
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp),
                        tint = colorResource(id = R.color.pale_green)
                    )
                    ClickableNavigationText(
                        normalText = "",
                        clickableText = "BACK TO LOGIN",
                        navigateTo = Screen.SignInScreen.route,
                        navController = navController
                    )
                }
            }
        }
    }
}
