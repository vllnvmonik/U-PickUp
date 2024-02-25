package com.echelon.upickup.appscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.echelon.upickup.R
import com.echelon.upickup.components.ClickableBoxNavigation
import com.echelon.upickup.components.CustomColorTitleText
import com.echelon.upickup.components.TitleText
import com.echelon.upickup.navigation.Screen


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
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            CustomColorTitleText(
                text = stringResource(R.string.inventory),
                color = R.color.profile_texts,
                20,
                FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(40.dp))
            ClickableBoxNavigation(onClick = {
                navController.navigate(Screen.InventoryBookScreen.route)
            }, icon = R.drawable.bookinventory_solid, text = stringResource(R.string.books))

            Spacer(modifier = Modifier.height(20.dp))
            ClickableBoxNavigation(onClick = {
                navController.navigate(Screen.InventoryModulesScreen.route)
            }, icon = R.drawable.book_open_solid, text = stringResource(R.string.modules))

            Spacer(modifier = Modifier.height(20.dp))
            ClickableBoxNavigation(onClick = {
                navController.navigate(Screen.InventoryUniformScreen.route)
            }, icon = R.drawable.shirt_solid, text = stringResource(R.string.uniforms))
        }
    }
}
