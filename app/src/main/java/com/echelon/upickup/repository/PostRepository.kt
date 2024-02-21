package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Post
import com.echelon.upickup.network.apiservice.PostApiService
import retrofit2.Response

class PostRepository {
    private val postApiService = RetrofitInstance.postApiService

    suspend fun getPosts(): Response<List<Post>> {
        return postApiService.getPosts()
    }
}