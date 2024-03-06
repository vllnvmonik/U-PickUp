package com.echelon.upickup.sharedprefs

import android.content.Context
import android.util.Log
import com.echelon.upickup.uistate.SignUpUI2State
import com.echelon.upickup.uistate.SignUpUI3State
import com.echelon.upickup.uistate.SignUpUIState

object SignUpDataManager {

    private const val PREF_NAME = "SignUpDataManager"

    fun saveSignUpUIState(context: Context, signUpUIState: SignUpUIState) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        with(signUpUIState) {
            editor.putString("name", name)
            editor.putString("middleName", middleName)
            editor.putString("lastName", lastName)
            editor.putString("gender", gender)
            editor.putString("age", age)
        }

        editor.apply()
        Log.d("SignUpDataManager", "UI State data saved: $signUpUIState")
    }

    fun saveSignUpUI2State(context: Context, signUpUI2State: SignUpUI2State) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        with(signUpUI2State) {
            editor.putString("program", program)
            editor.putString("department", department)
        }

        editor.apply()
        Log.d("SignUpDataManager", "UI2 State data saved: $signUpUI2State")
    }

    fun saveSignUpUI3State(context: Context, signUpUI3State: SignUpUI3State) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        with(signUpUI3State){
            editor.putString("email", email)
            editor.putString("idNumber", idNumber)
            editor.putString("password", password)
            editor.putString("confirmPassword", confirmPassword)
        }

        editor.apply()
        Log.d("SignUpDataManager", "UI3 State data saved: $signUpUI3State")
    }

    fun loadSignUpData(context: Context): Triple<SignUpUIState, SignUpUI2State, SignUpUI3State> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return Triple(
            SignUpUIState(
                // retrieve name, middleName, lastName, gender, and age from shared prefs
                // using getOrDefault to provide default empty strings if the keys are not found
                name = sharedPreferences.getString("name", "").toString(),
                middleName = sharedPreferences.getString("middleName", "").toString(),
                lastName = sharedPreferences.getString("lastName", "").toString(),
                gender = sharedPreferences.getString("gender", "").toString(),
                age = sharedPreferences.getString("age", "").toString()
            ),
            SignUpUI2State(
                program = sharedPreferences.getString("program", "").toString(),
                department = sharedPreferences.getString("department", "").toString()
            ),
            SignUpUI3State(
                email = sharedPreferences.getString("email", "").toString(),
                idNumber = sharedPreferences.getString("idNumber", "").toString(),
                password = sharedPreferences.getString("password", "").toString(),
                confirmPassword = sharedPreferences.getString("confirmPassword", "").toString()
            )
        )
    }


    fun clearSignUpData(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}