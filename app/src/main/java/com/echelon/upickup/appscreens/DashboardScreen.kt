package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.CustomImage
import com.echelon.upickup.components.FeedBox

@Composable
fun DashboardScreen(navController: NavHostController) {
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column (
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CustomImage(100,100, R.drawable.logo)
                FeedBox()
            }
        }
    }
}