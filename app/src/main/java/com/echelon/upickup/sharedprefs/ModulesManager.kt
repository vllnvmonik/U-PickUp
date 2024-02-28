package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.echelon.upickup.network.apimodel.ModulesResponse
import com.google.gson.Gson

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

    fun clearModulesResponse() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_MODULES_RESPONSE)
        editor.apply()
    }
}