package com.example.instagramclone.Data.Repository

import android.content.SharedPreferences

open class LoginRepository(private val prefs: SharedPreferences) {

    // checks if the login credentials match, no database used here so repository itself saves flag locally
    open fun login(email: String, password: String) : Boolean{
        val loginSuccess = (email == "user@example.com" && password == "password123")
        if(loginSuccess)
        {
            prefs.edit().putBoolean("loginFlag", true).apply()
        }
        return loginSuccess
    }

    // returns true if user had successfully "logged in" in the last session
    open fun isLoggedIn() : Boolean{
        return prefs.getBoolean("loginFlag", false)
    }
}