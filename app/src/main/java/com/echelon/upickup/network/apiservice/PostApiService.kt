package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}

