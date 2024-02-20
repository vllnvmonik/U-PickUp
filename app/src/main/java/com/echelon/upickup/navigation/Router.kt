package com.echelon.upickup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.echelon.upickup.R
import com.echelon.upickup.appscreens.CalendarScreen
import com.echelon.upickup.appscreens.DashboardScreen
import com.echelon.upickup.appscreens.ForgotPasswordScreen
import com.echelon.upickup.appscreens.ProfileScreen
import com.echelon.upickup.appscreens.SignInScreen
import com.echelon.upickup.appscreens.SignUpScreen
import com.echelon.upickup.viewmodel.ForgotPasswordViewModel
import com.echelon.upickup.viewmodel.ProfileViewModel
import com.echelon.upickup.viewmodel.SignInViewModel
import com.echelon.upickup.viewmodel.SignUpViewModel

sealed class Screen (val route: String) {
    object SignUpScreen: Screen("signup")
    object SignInScreen: Screen("signin")
    object ForgotPasswordScreen: Screen("forgot")

    object DashboardScreen: Screen("dashboard")
    object CalendarScreen: Screen("calendar")
//    object ChatScreen: Screen("chat")
    object ProfileScreen: Screen("profile")
    object AuthRoute: Screen("auth")
    object AppRoute: Screen("app")

}

sealed class BottomNavItem(
    val route: String,
    val selectedIcon: Int,
) {
    object DashboardItems: BottomNavItem("dashboard", R.drawable.house_solid)
    object CalendarItems: BottomNavItem("calendar", R.drawable.calendar_solid)
//    object ChatItems: BottomNavItem("chat", R.drawable.message_solid)
    object ProfileItems: BottomNavItem("profile", R.drawable.circle_user_solid)
}

@Composable
fun NavController(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.AuthRoute.route
    ) {
        // application navigation route before logging in
        navigation(startDestination = Screen.SignInScreen.route, route = Screen.AuthRoute.route){
            composable(Screen.SignUpScreen.route) {
                SignUpScreen(navController = navController, viewModel = SignUpViewModel(navController))
            }
            composable(Screen.SignInScreen.route) {
                SignInScreen(navController = navController, viewModel = SignInViewModel(navController))
            }
            composable(Screen.ForgotPasswordScreen.route){
                ForgotPasswordScreen(navController = navController, viewModel = ForgotPasswordViewModel(navController))
            }
        }
        // application navigation route after successful log in
        navigation(startDestination = Screen.DashboardScreen.route, route = Screen.AppRoute.route){
            composable(Screen.DashboardScreen.route) {
                DashboardScreen(navController = navController)
            }
            composable(Screen.CalendarScreen.route) {
                CalendarScreen(navController = navController)
            }
//            composable(Screen.ChatScreen.route) {
//                ChatScreen(navController = navController)
//            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navController = navController, viewModel = ProfileViewModel())
            }
        }
    }
}
