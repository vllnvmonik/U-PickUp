package com.echelon.upickup.sharedprefs

import android.content.Context
import android.util.Log
import com.echelon.upickup.uistate.SignUpUIState

object SignUpDataManager {

    private const val PREF_NAME = "SignUpDataManager"

    fun saveSignUpData(context: Context, signUpUIState: SignUpUIState) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        with(signUpUIState) {
            editor.putString("name", name)
            editor.putString("middleName", middleName)
            editor.putString("lastName", lastName)
            editor.putString("gender", gender)
            editor.putString("age", age)
            editor.putString("program", program)
            editor.putString("department", department)
            editor.putString("email", email)
            editor.putString("idNumber", idNumber)
            editor.putString("password", password)
            editor.putString("confirmPassword", confirmPassword)
        }
        editor.apply()
        Log.d("SignUpDataManager", "Data saved: $signUpUIState")
    }


    fun loadSignUpData(context: Context): SignUpUIState {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return SignUpUIState(
            name = sharedPreferences.getString("name", "") ?: "",
            middleName = sharedPreferences.getString("middleName", "") ?: "",
            lastName = sharedPreferences.getString("lastName", "") ?: "",
            gender = sharedPreferences.getString("gender", "") ?: "",
            age = sharedPreferences.getString("age", "") ?: "",
            program = sharedPreferences.getString("program", "") ?: "",
            department = sharedPreferences.getString("department", "") ?: "",
            email = sharedPreferences.getString("email", "") ?: "",
            idNumber = sharedPreferences.getString("idNumber", "") ?: "",
            password = sharedPreferences.getString("password", "") ?: "",
            confirmPassword = sharedPreferences.getString("confirmPassword", "") ?: ""
        )
    }

    fun clearSignUpData(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}