package com.echelon.upickup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.echelon.upickup.app.UPickUp
import com.echelon.upickup.sharedprefs.AuthManager
import com.echelon.upickup.sharedprefs.StudentDetailsManager
import com.echelon.upickup.sharedprefs.TokenManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenManager.initialize(this)
        StudentDetailsManager.initialize(this)
        AuthManager.initialize(this)
        installSplashScreen()
        setContent {
            UPickUp()
        }
    }
}

