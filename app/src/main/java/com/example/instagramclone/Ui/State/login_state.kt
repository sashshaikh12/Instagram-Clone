package com.example.instagramclone.Ui.State

enum class LoginState {
    NOT_LOGGED_IN,  // Need to show login screen
    LOGGED_IN,      // Can navigate to feed
    LOGIN_FAILED    // Show error on login screen
}