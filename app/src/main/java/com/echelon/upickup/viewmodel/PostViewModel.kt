package com.echelon.upickup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echelon.upickup.network.apimodel.Post
import com.echelon.upickup.repository.PostRepository
import com.echelon.upickup.sharedprefs.PostManager
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val postRepository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchPosts() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = postRepository.getPosts()
                if (response.isSuccessful){
                    val details = response.body()
                    _posts.value = details
                    details?.let {
                        PostManager.savePosts(it)
                    }
                    Log.d("PostViewModel", "Fetched posts: $details")
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
}
