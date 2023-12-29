package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.echelon.upickup.R
import com.echelon.upickup.components.BasicText

@Composable
fun ProfileScreen(navController: NavController) {
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        BasicText(text = "Profile")
    }
}