package com.echelon.upickup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.echelon.upickup.app.UPickUp
import com.echelon.upickup.sharedprefs.AuthManager
import com.echelon.upickup.sharedprefs.BooksManager
import com.echelon.upickup.sharedprefs.CalendarManager
import com.echelon.upickup.sharedprefs.ModulesManager
import com.echelon.upickup.sharedprefs.StudentDetailsManager
import com.echelon.upickup.sharedprefs.PostManager
import com.echelon.upickup.sharedprefs.TokenManager
import com.echelon.upickup.sharedprefs.UniformsManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TokenManager.initialize(this)
        StudentDetailsManager.initialize(this)
        PostManager.initialize(this)
        AuthManager.initialize(this)
        UniformsManager.initialize(this)
        ModulesManager.initialize(this)
        BooksManager.initialize(this)
        CalendarManager.initialize(this)
        installSplashScreen()
        setContent {
            UPickUp()
        }
    }
}

