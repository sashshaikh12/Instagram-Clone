package com.example.instagramclone.Ui

import com.example.instagramclone.Data.Local.Entity.FeedEntity

data class FeedResult(
    val posts: List<FeedEntity>,
    val isOffline: Boolean
)

