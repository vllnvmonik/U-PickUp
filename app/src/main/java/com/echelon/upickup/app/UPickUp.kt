package com.echelon.upickup.app

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.echelon.upickup.R
import com.echelon.upickup.components.BottomNavigationBar
import com.echelon.upickup.navigation.NavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UPickUp(){
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.white)
    ){
        AppUI()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUI() {
    val navController = rememberNavController()

    Scaffold (
        containerColor = colorResource(id = R.color.whitee),
        bottomBar = { BottomNavigationBar(navController) }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
        ){
            NavController(navController)
        }
    }
}