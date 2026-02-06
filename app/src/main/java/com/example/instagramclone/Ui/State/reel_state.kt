package com.example.instagramclone.Ui.State

import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity

sealed class ReelUiState {
    object Loading : ReelUiState()
    data class Success(
        val reels: List<ReelEntity>,
        val isOffline: Boolean
    ) : ReelUiState()
    data class Error(val message: String) : ReelUiState()
}
