package com.example.instagramclone.Viewmodel

import FeedRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Ui.State.FeedUiState
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: FeedRepository
) : ViewModel() {

    private val _feedState = MutableLiveData<FeedUiState>()
    val feedState: LiveData<FeedUiState> = _feedState
    val TAG = "LIKE_ERROR"

    init {
        fetchFeed()
    }

    private fun fetchFeed() {
        _feedState.value = FeedUiState.Loading

        viewModelScope.launch {
            try {
                val result = repository.getFeed()
                _feedState.value = FeedUiState.Success(
                    posts = result.posts,
                    isOffline = result.isOffline
                )
            } catch (e: Exception) {
                _feedState.value = FeedUiState.Error("Failed to load feed")
            }
        }
    }

    fun onLikeClicked(post: FeedEntity) {
        val updatedPost = post.copy(
            liked_by_user = !post.liked_by_user,
            like_count = if (post.liked_by_user)
                post.like_count - 1
            else
                post.like_count + 1
        )

        // Optimistic UI update
        updatePostInUi(updatedPost)

        viewModelScope.launch {
            val success = repository.toggleLike(updatedPost)
            if (!success) {
                // revert if API failed
                updatePostInUi(post)
                _feedState.value = FeedUiState.Error("Failed to update like")
            }
        }
    }

    private fun updatePostInUi(updatedPost: FeedEntity) {
        val currentPosts = (_feedState.value as? FeedUiState.Success)?.posts ?: return
        val updatedList = currentPosts.map {
            if (it.post_id == updatedPost.post_id) updatedPost else it
        }
        _feedState.value = FeedUiState.Success(updatedList, isOffline = false)
    }

}
