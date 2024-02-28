package com.echelon.upickup.appscreens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.FeedBox
import com.echelon.upickup.viewmodel.PostViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.echelon.upickup.sharedprefs.PostManager


@Composable
fun DashboardScreen(navController: NavHostController, viewModel: PostViewModel) {
//    val posts = viewModel.posts.observeAsState(emptyList())
    val isLoading = viewModel.isLoading.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }

    // Retrieve posts from SharedPrefs
    val postDetails = PostManager.getPosts()
    Log.d("DashboardScreen", "Post details from sharedprefs: $postDetails")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                postDetails?.let { response ->
                    FeedBox(posts = response)
                }
                if (isLoading.value) {
                    CircularProgressIndicator()
                } else {
                    // Display posts using FeedBox or any other UI component
//                    FeedBox(posts = posts.value)
//                    postDetails?.let { response ->
//                        FeedBox(posts = response)
//                    }
                    Log.d("DashboardScreen", "show em: $postDetails")
                }
            }
        }
    }
}

