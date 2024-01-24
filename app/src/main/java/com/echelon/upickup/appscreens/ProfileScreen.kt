package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.ClassDetailsBox
import com.echelon.upickup.components.CustomColorTitleText
import com.echelon.upickup.components.LogoutButton

@Composable
fun ProfileScreen(navController: NavHostController) {
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ){
                CustomColorTitleText(text = stringResource(R.string.profile_name), color = R.color.dark_green, 22)
                CustomColorTitleText(text = stringResource(R.string.profile_program), color = R.color.dark_green, 16)
                Spacer(modifier = Modifier.height(20.dp))
                ClassDetailsBox()
                Spacer(modifier = Modifier.height(30.dp))
                LogoutButton(text = stringResource(R.string.logout)) {
                    
                }
            }
        }
    }
}
