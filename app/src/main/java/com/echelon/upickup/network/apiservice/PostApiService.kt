package com.echelon.upickup.network.apiservice

import com.echelon.upickup.network.apimodel.Post
import com.echelon.upickup.network.apimodel.PostLikeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}

interface PostLikesApiService {
    @POST("posts/{id}/like")
    suspend fun likePost(@Path("id") postId: String): Result<PostLikeResponse>
}

