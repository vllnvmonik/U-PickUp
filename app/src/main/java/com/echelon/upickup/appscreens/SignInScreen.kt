package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.echelon.upickup.R
import com.echelon.upickup.components.LogoImage
import com.echelon.upickup.components.TitleText

@Composable
fun SignInScreen(){
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = Color.White
    ){
        Column (modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(100.dp))
            LogoImage()
            TitleText(text = stringResource(R.string.hey_there))
        }
    }
}