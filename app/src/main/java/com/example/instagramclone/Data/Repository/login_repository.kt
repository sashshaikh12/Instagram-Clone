package com.example.instagramclone.Data.Repository

import android.content.SharedPreferences

class LoginRepository(private val prefs: SharedPreferences) {
    private val sharEdit = prefs.edit()

    fun login(email: String, password: String) : Boolean{
        val loginSuccess = (email == "user@example.com" && password == "password123")
        sharEdit.putBoolean("loginFlag", loginSuccess)
        sharEdit.apply()
        return loginSuccess
    }
}