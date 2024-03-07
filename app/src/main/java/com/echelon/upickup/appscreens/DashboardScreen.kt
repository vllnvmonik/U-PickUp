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
import com.echelon.upickup.R
import com.echelon.upickup.components.FeedBox
import com.echelon.upickup.viewmodel.PostViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.echelon.upickup.navigation.Screen
import com.echelon.upickup.sharedprefs.PostManager
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun DashboardScreen(navController: NavHostController,viewModel: PostViewModel) {
    val posts = viewModel.posts.observeAsState(emptyList())
    val isLoading = viewModel.isLoading.observeAsState(false)
    Log.d("DashboardScreen", "posts from viewModel.posts: $posts")

//    val coroutineScope = rememberCoroutineScope()
    val backStackEntry = navController.currentBackStackEntryAsState().value
    LaunchedEffect(backStackEntry) {
        if (backStackEntry?.destination?.route == Screen.DashboardScreen.route) {
            viewModel.fetchPosts()
        }
    }

    //refreshhs
    val refreshingState = rememberSwipeRefreshState(isRefreshing = isLoading.value)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.whitee)
    ) {
        SwipeRefresh(
            state = refreshingState,
            onRefresh = { viewModel.fetchPosts() }
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
                    FeedBox(
                        posts = posts.value
                    ) { postId ->
                        viewModel.likePost(postId)
                        viewModel.fetchPosts()
                    }
                }
            }
        }
    }
}