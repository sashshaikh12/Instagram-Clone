package com.example.instagramclone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import com.example.instagramclone.Data.Repository.LoginRepository
import com.example.instagramclone.Ui.State.LoginState
import com.example.instagramclone.Viewmodel.LoginViewModel
import com.example.instagramclone.databinding.LoginFragmentBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginFragmentBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sharedPrefs = this.getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        //sharedPrefs.edit().clear().apply()
        val loginRepo = LoginRepository(sharedPrefs)
        loginViewModel = LoginViewModel(loginRepo)


        // when user enters email and password ui passes the user action to viewmodel
        binding.LoginButton.setOnClickListener {
            val email = binding.Email.editText?.text.toString()
            val password = binding.Password.editText?.text.toString()
            loginViewModel.login(email, password)
        }

        // ui observes livedata loginStatus in viewmodel to decide which screen or toast to display to user
        loginViewModel.loginState.observe(this){status ->
            if(status == LoginState.LOGGED_IN)
            {
                //Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                //requireView().findNavController().navigate(R.id.action_loginFragment_to_feedFragment)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else if(status == LoginState.LOGIN_FAILED){
                Toast.makeText(this, "Invalid Credentials. Please try again.", Toast.LENGTH_SHORT).show()
            }
            // else we status == NOT_LOGGED_IN, which means stay at login page, which is anyways the start of navgraph
        }


    }

}