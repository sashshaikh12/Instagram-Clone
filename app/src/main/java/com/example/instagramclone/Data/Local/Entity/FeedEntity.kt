package com.example.instagramclone.Data.Local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class FeedEntity(
    @PrimaryKey
    val post_id: String,
    val user_name: String,
    val user_image: String,
    val post_image: String,
    val like_count: Int,
    val liked_by_user: Boolean
)