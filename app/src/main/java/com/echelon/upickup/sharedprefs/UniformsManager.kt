package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.echelon.upickup.network.apimodel.UniformsResponse
import com.google.gson.Gson

object UniformsManager {
    private const val PREF_NAME = "UniformsPrefs"
    private const val KEY_UNIFORMS_RESPONSE = "uniforms_response"

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUniformsResponse(uniformsResponse: UniformsResponse?) {
        val serializedUniformsResponse = gson.toJson(uniformsResponse)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_UNIFORMS_RESPONSE, serializedUniformsResponse)
        editor.apply()
    }

    fun getUniformsResponse(): UniformsResponse? {
        val serializedUniformsResponse = sharedPreferences.getString(KEY_UNIFORMS_RESPONSE, null)
        return gson.fromJson(serializedUniformsResponse, UniformsResponse::class.java)
    }

    fun clearUniformsResponse() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_UNIFORMS_RESPONSE)
        editor.apply()
    }
}
