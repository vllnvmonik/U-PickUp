package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.echelon.upickup.network.apimodel.BooksResponse
import com.google.gson.Gson

object BooksManager {
    private const val PREF_NAME = "BooksPrefs"
    private const val KEY_BOOKS_RESPONSE = "books_response"

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveBooksResponse(booksResponse: BooksResponse?) {
        val serializedBooksResponse = gson.toJson(booksResponse)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_BOOKS_RESPONSE, serializedBooksResponse)
        editor.apply()
    }

    fun getBooksResponse(): BooksResponse? {
        val serializedBooksResponse = sharedPreferences.getString(KEY_BOOKS_RESPONSE, null)
        return gson.fromJson(serializedBooksResponse, BooksResponse::class.java)
    }

    fun clearBooksResponse() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_BOOKS_RESPONSE)
        editor.apply()
    }
}
