package com.example.instagramclone.Viewmodel

import FeedRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity
import com.example.instagramclone.Data.Repository.ReelRepository
import com.example.instagramclone.Ui.State.FeedUiState
import com.example.instagramclone.Ui.State.ReelUiState
import kotlinx.coroutines.launch

class ReelViewModel(
    private val repository: ReelRepository
) : ViewModel() {

    private val _reelState = MutableLiveData<ReelUiState>()
    val reelState: LiveData<ReelUiState> = _reelState
    val TAG = "LIKE_ERROR"

    init {
        fetchReel()
    }

    private fun fetchReel() {
        _reelState.value = ReelUiState.Loading

        viewModelScope.launch {
            try {
                val result = repository.getReel()
                _reelState.value = ReelUiState.Success(
                    reels = result.reels,
                    isOffline = result.isOffline
                )
            } catch (e: Exception) {
                _reelState.value = ReelUiState.Error("Failed to load Reels")
            }
        }
    }

    fun onLikeClicked(reel: ReelEntity) {
        val updatedReel = reel.copy(
            liked_by_user = !reel.liked_by_user,
            like_count = if (reel.liked_by_user)
                reel.like_count - 1
            else
                reel.like_count + 1
        )

        // Optimistic UI update
        updateReelInUi(updatedReel)

        viewModelScope.launch {
            val success = repository.toggleLike(updatedReel)
            if (!success) {
                // revert if API failed
                updateReelInUi(reel)
                _reelState.value = ReelUiState.Error("Failed to update like")
            }
        }
    }

    private fun updateReelInUi(updatedReel: ReelEntity) {
        val currentReels = (_reelState.value as? ReelUiState.Success)?.reels ?: return
        val updatedList = currentReels.map {
            if (it.reel_id == updatedReel.reel_id) updatedReel else it
        }
        _reelState.value = ReelUiState.Success(updatedList, isOffline = false)
    }

}
