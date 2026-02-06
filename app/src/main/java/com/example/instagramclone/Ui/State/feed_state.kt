package com.example.instagramclone.Ui.State

import com.example.instagramclone.Data.Local.Entity.FeedEntity

sealed class FeedUiState {
    object Loading : FeedUiState()
    data class Success(
        val posts: List<FeedEntity>,
        val isOffline: Boolean
    ) : FeedUiState()
    data class Error(val message: String) : FeedUiState()
}
