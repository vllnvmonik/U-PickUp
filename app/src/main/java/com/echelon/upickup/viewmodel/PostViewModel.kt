package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Post
import com.echelon.upickup.network.apimodel.PostLikeResponse
import com.echelon.upickup.repository.PostRepository
import com.echelon.upickup.sharedprefs.PostManager
import com.echelon.upickup.sharedprefs.StudentDetailsManager
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    private val postRepository = PostRepository()
    val studentDetails = StudentDetailsManager.getStudentDetails()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchPosts() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = postRepository.getPosts()
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList() // Handle null response body
                    _posts.value = posts
                    Log.d("PostViewModel", "Fetched posts: $posts")
                    PostManager.savePosts(posts)
                } else {
                    Log.e("PostViewModel", "Failed to fetch posts: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error fetching posts: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }


    private val _likePostResult = MutableLiveData<Result<PostLikeResponse>>()

    fun likePost(postId: String) {
        viewModelScope.launch {
            try {
                val result = postRepository.likePost(postId, StudentDetailsManager.getStudentDetails()?.id)
                Log.d("PostViewModel", "Student ID: ${StudentDetailsManager.getStudentDetails()?.id}, Post ID: $postId")
                _likePostResult.value = result
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error liking post: ${e.message}", e)
                _likePostResult.value = Result.failure(e)
            }
        }
    }
}
