package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.ModulesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ModulesManager {
    private const val PREF_NAME = "ModulesPrefs"
    private const val KEY_MODULES_RESPONSE = "modules_response"

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveModulesResponse(modulesResponse: ModulesResponse?) {
        val serializedModulesResponse = gson.toJson(modulesResponse)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_MODULES_RESPONSE, serializedModulesResponse)
        editor.apply()
    }

    fun getModulesResponse(): ModulesResponse? {
        val serializedModulesResponse = sharedPreferences.getString(KEY_MODULES_RESPONSE, null)
        return gson.fromJson(serializedModulesResponse, ModulesResponse::class.java)
    }
    fun saveModulesByYear(modulesByYear: List<Modules>?) {
        val serializedModulesByYear = gson.toJson(modulesByYear)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_MODULES_RESPONSE, serializedModulesByYear)
        editor.apply()
        Log.d("MODULESsmanager", " $serializedModulesByYear")
    }
    fun getModulesByYear(): List<Modules>? {
        val serializedModulesByYear = sharedPreferences.getString(KEY_MODULES_RESPONSE, null)
        val type = object : TypeToken<List<Modules>>() {}.type
        return gson.fromJson<List<Modules>>(serializedModulesByYear, type)
    }
    fun clearModulesResponse() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_MODULES_RESPONSE)
        editor.apply()
    }
}