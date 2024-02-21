package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.echelon.upickup.model.StudentDetails
import com.google.gson.Gson

object StudentDetailsManager {
    private const val PREF_NAME = "StudentDetailsPrefs"
    private const val KEY_STUDENT_DETAILS = "student_details"

    private val gson = Gson()

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveStudentDetails(studentDetails: StudentDetails) {
        val serializedStudentDetails = gson.toJson(studentDetails)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_STUDENT_DETAILS, serializedStudentDetails)
        editor.apply()
    }

    fun getStudentDetails(): StudentDetails? {
        val serializedStudentDetails = sharedPreferences.getString(KEY_STUDENT_DETAILS, null)
        return gson.fromJson(serializedStudentDetails, StudentDetails::class.java)
    }

    fun clearStudentDetails() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_STUDENT_DETAILS)
        editor.apply()
    }
}