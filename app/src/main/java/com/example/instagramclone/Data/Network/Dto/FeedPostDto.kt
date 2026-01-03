package com.example.instagramclone.Data.Network.Dto

data class FeedPostDto(
    val post_id : String,
    val user_name: String,
    val user_image: String,
    val post_image: String,
    val like_count: Int,
    val liked_by_user: Boolean
)
