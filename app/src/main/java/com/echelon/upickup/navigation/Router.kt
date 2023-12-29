package com.echelon.upickup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.echelon.upickup.appscreens.DashboardScreen
import com.echelon.upickup.appscreens.SignInScreen
import com.echelon.upickup.appscreens.SignUpScreen

sealed class Screen (val route: String) {
    object SignUpScreen: Screen("signup")
    object SignInScreen: Screen("signin")
    object DashboardScreen: Screen("dashboard")
}


@Composable
fun NavController() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route
    ) {
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
        composable(Screen.DashboardScreen.route) {
            DashboardScreen(navController = navController)
        }
    }
}