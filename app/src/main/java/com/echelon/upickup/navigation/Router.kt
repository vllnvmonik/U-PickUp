package com.echelon.upickup.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.echelon.upickup.R
import com.echelon.upickup.appscreens.CalendarScreen
import com.echelon.upickup.appscreens.DashboardScreen
import com.echelon.upickup.appscreens.ForgotPasswordScreen
import com.echelon.upickup.appscreens.InventoryBooksScreen
import com.echelon.upickup.appscreens.InventoryModulesScreen
import com.echelon.upickup.appscreens.InventoryScreen
import com.echelon.upickup.appscreens.InventoryUniformScreen
import com.echelon.upickup.appscreens.ProfileScreen
import com.echelon.upickup.appscreens.SignInScreen
import com.echelon.upickup.appscreens.SignUpScreen
import com.echelon.upickup.appscreens.SignUpScreenThree
import com.echelon.upickup.appscreens.SignUpScreenTwo
import com.echelon.upickup.viewmodel.PostViewModel
import com.echelon.upickup.sharedprefs.AuthManager
import com.echelon.upickup.viewmodel.CalendarViewModel
import com.echelon.upickup.viewmodel.ForgotPasswordViewModel
import com.echelon.upickup.viewmodel.InventoryBooksViewModel
import com.echelon.upickup.viewmodel.InventoryModulesViewModel
import com.echelon.upickup.viewmodel.InventoryUniformsViewModel
import com.echelon.upickup.viewmodel.ProfileViewModel
import com.echelon.upickup.viewmodel.SignInViewModel
import com.echelon.upickup.viewmodel.SignUpViewModel

sealed class Screen (val route: String) {
    object SignUpScreen: Screen("signup")
    object SignUpScreenTwo: Screen("signupTwo")
    object SignUpScreenThree: Screen("signupThree")

    object SignInScreen: Screen("signin")
    object ForgotPasswordScreen: Screen("forgot")

    object DashboardScreen: Screen("dashboard")
    object CalendarScreen: Screen("calendar")
//    object ChatScreen: Screen("chat")
    object InventoryScreen: Screen("inventory")
    object InventoryBookScreen: Screen("inventory-books")
    object InventoryModulesScreen: Screen("inventory-modules")
    object InventoryUniformScreen: Screen("inventory-uniform")

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
    object InventoryItems: BottomNavItem("inventory", R.drawable.book_solid)
    object ProfileItems: BottomNavItem("profile", R.drawable.circle_user_solid)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavController(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = determineStartDestination()
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
            composable(Screen.SignUpScreenTwo.route){
                SignUpScreenTwo(navController, viewModel = SignUpViewModel(navController))
            }
            composable(Screen.SignUpScreenThree.route){
                SignUpScreenThree(navController, viewModel = SignUpViewModel(navController))
            }
        }
        // application navigation route after successful log in
        navigation(startDestination = Screen.DashboardScreen.route, route = Screen.AppRoute.route){
            composable(Screen.DashboardScreen.route) {
                DashboardScreen(navController = navController, viewModel = PostViewModel())
            }
            composable(Screen.CalendarScreen.route) {
                CalendarScreen(navController = navController, viewModel = CalendarViewModel())
            }
            composable(Screen.InventoryScreen.route){
                InventoryScreen(navController = navController)
            }
            composable(Screen.InventoryBookScreen.route){
                InventoryBooksScreen(viewModel = InventoryBooksViewModel())
            }
            composable(Screen.InventoryModulesScreen.route){
                InventoryModulesScreen(viewModel = InventoryModulesViewModel())
            }
            composable(Screen.InventoryUniformScreen.route){
                InventoryUniformScreen(viewModel = InventoryUniformsViewModel())
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navController = navController, viewModel = ProfileViewModel())
            }
        }
    }
}

@Composable
fun determineStartDestination(): String {
    return if (AuthManager.isLoggedIn()) {
        Screen.AppRoute.route
    } else {
        Screen.AuthRoute.route
    }
}