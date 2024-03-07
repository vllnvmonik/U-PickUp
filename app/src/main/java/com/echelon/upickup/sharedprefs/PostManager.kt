package com.echelon.upickup.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.echelon.upickup.model.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PostManager {
    private const val PREF_NAME = "PostPrefs"
    private const val KEY_POSTS = "posts_list"

    private val gson = Gson()

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isPostLiked(postId: String): Boolean {
        return sharedPreferences.getBoolean(postId, false)
    }

    fun savePostLiked(postId: String, isLiked: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(postId, isLiked)
            apply()
        }
    }
//    fun clearPosts() {
//        val editor = sharedPreferences.edit()
//        editor.remove(KEY_POSTS)
//        editor.apply()
//    }
}
