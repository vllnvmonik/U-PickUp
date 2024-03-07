package com.echelon.upickup.repository

import com.echelon.upickup.network.RetrofitInstance
import com.echelon.upickup.network.apimodel.Post
import com.echelon.upickup.network.apimodel.PostLikeResponse
import com.echelon.upickup.sharedprefs.StudentDetailsManager
import retrofit2.Response

class PostRepository {
    private val postApiService = RetrofitInstance.postApiService
    private val postLikesApiService = RetrofitInstance.postLikesApiService


//    suspend fun getPosts(): Response<List<Post>> {
//        return postApiService.getPosts()
//    }

    suspend fun getPosts(): Response<List<Post>> {
        val studentId = getStudentId()
        return postApiService.getPosts(studentId)
    }

    private fun getStudentId(): String? {
        val studentDetails = StudentDetailsManager.getStudentDetails()
        return studentDetails?.id
    }

    suspend fun likePost(postId: String, studentId: String?): Result<PostLikeResponse> {
        return try {
            val response = postLikesApiService.likePost(postId, studentId)
            Result.success(response.body()!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


//    suspend fun likePost(postId: String): Result<PostLikeResponse> {
//         return postLikesApiService.likePost(postId)
//    }
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