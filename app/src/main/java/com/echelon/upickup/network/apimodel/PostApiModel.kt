package com.echelon.upickup.network.apimodel


data class Post(
    val id: String,
    val post_content: String,
    val likes_count: Int,
    val created_at: String,
    val liked_by_user: Boolean
)

data class PostLikeResponse(
    val postId: String,
    val likesCount: Int
)
