package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.echelon.upickup.R



@Preview
@Composable
fun InventoryScreenPreview() {
    InventoryScreen(navController = rememberNavController())
}

@Composable
fun InventoryScreen(navController: NavHostController) {
    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
    }
}
