package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.echelon.upickup.network.apimodel.Modules
import com.echelon.upickup.network.apimodel.Uniform
import com.echelon.upickup.network.apimodel.UniformsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    fun saveUniformsByYear(uniformsByYear: List<Uniform>?) {
        val serializedUniformsByYear = gson.toJson(uniformsByYear)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_UNIFORMS_RESPONSE, serializedUniformsByYear)
        editor.apply()
        Log.d("uniformsByYear", " $serializedUniformsByYear")
    }
    fun getUniformsByYear(): List<Uniform>? {
        val serializedUniformsByYear = sharedPreferences.getString(KEY_UNIFORMS_RESPONSE, null)
        val type = object : TypeToken<List<Uniform>>() {}.type
        return gson.fromJson<List<Uniform>>(serializedUniformsByYear, type)
    }

    fun clearUniformsResponse() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_UNIFORMS_RESPONSE)
        editor.apply()
    }
}
