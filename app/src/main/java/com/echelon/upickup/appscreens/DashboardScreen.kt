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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.echelon.upickup.R
import com.echelon.upickup.components.CustomImage
import com.echelon.upickup.components.FeedBox
import com.echelon.upickup.network.apimodel.Post
import com.echelon.upickup.viewmodel.PostViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun DashboardScreen(navController: NavHostController, viewModel: PostViewModel) {
    val posts: List<Post> by viewModel.posts.observeAsState(emptyList())
    Log.d("PostScreen", "show em: $posts")
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)


    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }


    Surface (modifier = Modifier
        .fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(10.dp))
                CustomImage(100,100, R.drawable.logo)
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    FeedBox(posts = posts)
                }
            }
        }
    }
}
