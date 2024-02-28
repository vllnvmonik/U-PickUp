package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.echelon.upickup.components.LogoImage
import com.echelon.upickup.network.apimodel.Event
import com.google.gson.Gson

object CalendarManager {
    private const val PREF_NAME = "CalendarPrefs"
    private const val KEY_EVENTS = "events"

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveEvents(events: List<Event>) {
        val serializedEvents = gson.toJson(events)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_EVENTS, serializedEvents)
        editor.apply()
        Log.d("CalendarManager", "Fetched events: $serializedEvents")
    }

    fun getEvents(): List<Event> {
        val serializedEvents = sharedPreferences.getString(KEY_EVENTS, null)
        return gson.fromJson(serializedEvents, Array<Event>::class.java).toList()
    }

    fun clearEvents() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_EVENTS)
        editor.apply()
    }
}