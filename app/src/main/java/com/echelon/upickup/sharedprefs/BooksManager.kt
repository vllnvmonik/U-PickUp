package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.echelon.upickup.network.apimodel.Books
import com.echelon.upickup.network.apimodel.BooksResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    fun saveBooksByYear(booksByYear: List<Books>?) {
        val serializedBooksByYear = gson.toJson(booksByYear)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_BOOKS_RESPONSE, serializedBooksByYear)
        editor.apply()
        Log.d("booksmanager", " $serializedBooksByYear")
    }
    fun getBooksByYear(): List<Books>? {
        val serializedBooksByYear = sharedPreferences.getString(KEY_BOOKS_RESPONSE,
            null)
        val type = object : TypeToken<List<Books>>() {}.type
        return gson.fromJson<List<Books>>(serializedBooksByYear, type)
    }
    fun clearBooksResponse() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_BOOKS_RESPONSE)
        editor.apply()
    }
}
