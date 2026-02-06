package com.example.instagramclone.Data

import android.widget.Button
import android.widget.ImageView

data class FeedDataClass(
    var avatar: Int,
    var username: String,
    var postImage: Int,
    var likeButton: String,
    var likeCount: String,
    var description: String
)
