package com.example.instagramclone.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instagramclone.Data.Repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    fun login(email: String, password: String) {
        val success = repository.login(email, password)
        _loginState.value = success
    }
}