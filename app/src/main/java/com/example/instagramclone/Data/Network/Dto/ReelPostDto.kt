package com.example.instagramclone.Data.Network.Dto

data class ReelPostDto (
    val reel_id : String,
    val user_name: String,
    val user_image: String,
    val reel_video: String,
    val like_count: Int,
    val liked_by_user: Boolean
)