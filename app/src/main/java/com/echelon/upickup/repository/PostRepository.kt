package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Post
import com.echelon.upickup.network.apimodel.PostLikeResponse
import com.echelon.upickup.network.apiservice.PostApiService
import retrofit2.Response

class PostRepository {
    private val postApiService = RetrofitInstance.postApiService
    private val postLikesApiService = RetrofitInstance.postLikesApiService


    suspend fun getPosts(): Response<List<Post>> {
        return postApiService.getPosts()
    }

    suspend fun likePost(postId: String): Result<PostLikeResponse> {
         return postLikesApiService.likePost(postId)
    }
//        return try {
//            val response = postLikesApiService.likePost(postId)
//            if (response.isSuccessful) {
//                val postLikeResponse = response.body()
//                Result.Success(postLikeResponse)
//            } else {
//                Result.Error(Exception("Failed to like post"))
//            }
//        } catch (e: Exception) {
//            Result.Error(e)
//        }
//    }
}