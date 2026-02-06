package com.example.instagramclone.Data.Local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reels")
data class ReelEntity(
    @PrimaryKey
    val reel_id: String,
    val user_name: String,
    val user_image: String,
    val reel_video: String,
    val like_count: Int,
    val liked_by_user: Boolean
)