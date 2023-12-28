package com.echelon.upickup.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.echelon.upickup.R
import com.echelon.upickup.appscreens.SignUpScreen

@Composable
fun UPickUp(){
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        SignUpScreen()
    }
}