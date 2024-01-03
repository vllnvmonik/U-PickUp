package com.echelon.upickup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.echelon.upickup.R
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
    val selectedIcon: Int,
) {
    object DashboardItems: BottomNavItem("dashboard", R.drawable.house_solid)
    object CalendarItems: BottomNavItem("calendar", R.drawable.calendar_solid)
    object ChatItems: BottomNavItem("chat", R.drawable.message_solid)
    object ProfileItems: BottomNavItem("profile", R.drawable.circle_user_solid)
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

//@Composable
//fun ShowBottomNavBar(navController: NavHostController) {
//    val currentRoute = navController.currentBackStackEntry?.destination?.route
//    Log.d("sdfsdfsdf", "riute $currentRoute")
//
//    if (currentRoute in listOf(
//            Screen.DashboardScreen.route,
//            Screen.CalendarScreen.route,
//            Screen.ChatScreen.route,
//            Screen.ProfileScreen.route
//        )) {
//        BottomNavigationBar(navController)
//    }
//}
