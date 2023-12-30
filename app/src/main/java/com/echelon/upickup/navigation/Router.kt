package com.echelon.upickup.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.echelon.upickup.appscreens.CalendarScreen
import com.echelon.upickup.appscreens.ChatScreen
import com.echelon.upickup.appscreens.DashboardScreen
import com.echelon.upickup.appscreens.ProfileScreen
import com.echelon.upickup.appscreens.SignInScreen
import com.echelon.upickup.appscreens.SignUpScreen

sealed class Screen (val route: String) {
    object SignUpScreen: Screen("signup")
    object SignInScreen: Screen("signin")
    object DashboardScreen: Screen("dashboard")
    object CalendarScreen: Screen("calendar")
    object ChatScreen: Screen("chat")
    object ProfileScreen: Screen("profile")
}

sealed class BottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
) {
    object DashboardItems: BottomNavItem("dashboard", Icons.Filled.Home)
    object CalendarItems: BottomNavItem("calendar", Icons.Filled.DateRange)
    object ChatItems: BottomNavItem("chat", Icons.Filled.Email)
    object ProfileItems: BottomNavItem("profile", Icons.Filled.Person)
}

@Composable
fun NavController(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.DashboardScreen.route
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
        composable(Screen.CalendarScreen.route) {
            CalendarScreen(navController = navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}
