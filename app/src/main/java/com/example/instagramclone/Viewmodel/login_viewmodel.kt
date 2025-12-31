package com.example.instagramclone.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instagramclone.Data.Repository.LoginRepository
import com.example.instagramclone.Ui.State.LoginState

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    init {
        checkLoginStatus() // checks the login status of user when the viewmodel is created
    }

    // checks the login status and assigns the respective value to loginState
    private fun checkLoginStatus(){
        val isLoggedIn = repository.isLoggedIn()
        _loginState.value = if(isLoggedIn){
            LoginState.LOGGED_IN
        }
        else{
            LoginState.NOT_LOGGED_IN
        }
    }

    // passes the email and password entered by user to the repository and gets back the loginStatus
    fun login(email: String, password: String) {
        val success = repository.login(email, password)
        _loginState.value = if(success){
            LoginState.LOGGED_IN
        }
        else{
            LoginState.LOGIN_FAILED
        }
    }
}