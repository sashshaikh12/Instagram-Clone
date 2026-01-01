package com.example.instagramclone

import com.example.instagramclone.Data.Repository.LoginRepository
import com.example.instagramclone.FakeClasses.FakeSharedPreferences
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class LoginRepositoryTest {

    private lateinit var fakePrefs: FakeSharedPreferences
    private lateinit var repository: LoginRepository

    @Before
    fun setup() {
        fakePrefs = FakeSharedPreferences()
        repository = LoginRepository(fakePrefs)
    }

    //All below tescases are positive cases for Login():

    @Test
    fun `Valid credentials should login and save results successfully`(){
        val IsSuccess = repository.login("user@example.com", "password123")
        assertEquals(true, IsSuccess)
        assertEquals(true, fakePrefs.getBoolean("loginFlag", false))
    }

    //All below testcases are negetive cases for Login():

    @Test
    fun `Blank email should fail login and save state properly`(){
        val IsSuccess = repository.login("", "password123")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `Blank password should fail login and save state properly`(){
        val IsSuccess = repository.login("user@example.com", "")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `Blank email and password should fail login and save state properly`(){
        val IsSuccess = repository.login("", "")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `invalid email should fail login and save state properly`(){
        val IsSuccess = repository.login("user1@example.com", "password123")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `invalid password should fail login and save state properly`(){
        val IsSuccess = repository.login("user@example.com", "password")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `invalid email and password should fail login and save state properly`(){
        val IsSuccess = repository.login("user1@example.com", "password")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `invalid Case sensitive email should fail login and save state properly`(){
        val IsSuccess = repository.login("User@example.com", "password123")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `invalid Case sensitive password should fail login and save state properly`(){
        val IsSuccess = repository.login("user@exampl.com", "Password123")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    @Test
    fun `invalid Case sensitive email and password should fail login and save state properly`(){
        val IsSuccess = repository.login("User@exampl.com", "Password123")
        assertEquals(false, IsSuccess)
        assertEquals(false, fakePrefs.getBoolean("loginFlag", false))
    }

    // below are positive cases for isLoggedIn()

    @Test
    fun `return true if user has logged in successfully already`(){
        fakePrefs.edit().putBoolean("loginFlag", true).apply()
        assertEquals(true, repository.isLoggedIn())
    }

    @Test
    fun `return false if user has not logged in`(){
        assertEquals(false, repository.isLoggedIn())
    }

}