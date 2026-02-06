package com.example.instagramclone.Ui

import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity

data class ReelResult(
    val reels: List<ReelEntity>,
    val isOffline: Boolean
)

