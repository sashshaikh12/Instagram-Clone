package com.example.instagramclone

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.instagramclone.FakeClasses.FakeLoginRepository
import com.example.instagramclone.FakeClasses.FakeSharedPreferences
import com.example.instagramclone.Ui.State.LoginState
import com.example.instagramclone.Viewmodel.LoginViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeLoginRepo: FakeLoginRepository
    private lateinit var fakeSharedPreferences: FakeSharedPreferences
    private lateinit var viewModelTest: LoginViewModel

    @Before
    fun setup(){
        fakeSharedPreferences = FakeSharedPreferences()
        fakeLoginRepo = FakeLoginRepository(fakeSharedPreferences)
        //viewModelTest = LoginViewModel(fakeLoginRepo)
    }

    // Below are the positive testcases for checkLoginStatus()

    @Test
    fun `loginStatus must be LOGGED_IN if user has successfully logged in`(){
        fakeLoginRepo.LoggedIn = true
        viewModelTest = LoginViewModel(fakeLoginRepo)
        assertEquals<LoginState?>(LoginState.LOGGED_IN, viewModelTest.loginState.value)
    }

    // below are the negetive testcases for checkLoginStatus()

    @Test
    fun `loginStatus must be NOT_LOGGED_IN if user has not successfully logged in`(){
        viewModelTest = LoginViewModel(fakeLoginRepo)
        assertEquals<LoginState?>(LoginState.NOT_LOGGED_IN, viewModelTest.loginState.value)
    }

    // Below are the positive testcases for login()

    @Test
    fun `Loginstatus should be LOGGED_IN if user enters correct credentials`(){
        fakeLoginRepo.loginShouldSucceed = true
        viewModelTest = LoginViewModel(fakeLoginRepo)
        viewModelTest.login("anything", "anything")
        assertEquals<LoginState?>(LoginState.LOGGED_IN, viewModelTest.loginState.value)
    }

    // Below are the negetive testcases for login()

    @Test
    fun `Loginstatus should be LOGIN_FAILED if user enters invalid credentials`(){
        // fake repo has already set loginShouldSuceed to false so i am not reassigning here
        viewModelTest = LoginViewModel(fakeLoginRepo)
        viewModelTest.login("anything", "anything")
        assertEquals<LoginState?>(LoginState.LOGIN_FAILED, viewModelTest.loginState.value)
    }
}