package com.example.instagramclone.FakeClasses

import com.example.instagramclone.Data.Repository.LoginRepository

class FakeLoginRepository(private val prefs: FakeSharedPreferences) : LoginRepository(prefs) {
    var LoggedIn = false // tells if user has logged in or not before
    var loginShouldSucceed = false // in controll of developer to login or fail the login according to test cases

    override fun isLoggedIn(): Boolean {
        return LoggedIn
    }

    override fun login(email: String, password: String): Boolean {
        return loginShouldSucceed
    }
}