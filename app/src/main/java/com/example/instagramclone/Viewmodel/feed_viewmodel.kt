package com.example.instagramclone.Viewmodel

import FeedRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.Ui.State.FeedUiState
import kotlinx.coroutines.launch

class FeedViewModel(
    private val repository: FeedRepository
) : ViewModel() {

    private val _feedState = MutableLiveData<FeedUiState>()
    val feedState: LiveData<FeedUiState> = _feedState

    init {
        fetchFeed()
    }

    private fun fetchFeed() {
        _feedState.value = FeedUiState.Loading

        viewModelScope.launch {
            try {
                val posts = repository.getFeed()
                _feedState.value = FeedUiState.Success(posts)
            } catch (e: Exception) {
                _feedState.value = FeedUiState.Error("Failed to load feed")
            }
        }
    }
}
