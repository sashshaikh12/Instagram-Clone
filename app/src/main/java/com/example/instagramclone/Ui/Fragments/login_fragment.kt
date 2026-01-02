package com.example.instagramclone.Ui.Fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.instagramclone.Data.Repository.LoginRepository
import com.example.instagramclone.R
import com.example.instagramclone.Ui.State.LoginState
import com.example.instagramclone.Viewmodel.LoginViewModel
import com.example.instagramclone.databinding.LoginFragmentBinding


class LoginFragment : Fragment(R.layout.login_fragment){
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = requireContext().getSharedPreferences("loginPref", Context.MODE_PRIVATE)
        val loginRepo = LoginRepository(sharedPrefs)
        loginViewModel = LoginViewModel(loginRepo)

        _binding = LoginFragmentBinding.bind(view)

        // when user enters email and password ui passes the user action to viewmodel
        _binding!!.LoginButton.setOnClickListener {
            val email = _binding!!.Email.editText?.text.toString()
            val password = _binding!!.Password.editText?.text.toString()
            loginViewModel.login(email, password)
        }

        // ui observes livedata loginStatus in viewmodel to decide which screen or toast to display to user
        loginViewModel.loginState.observe(viewLifecycleOwner){status ->
            if(status == LoginState.LOGGED_IN)
            {
                //Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                requireView().findNavController().navigate(R.id.action_loginFragment_to_feedFragment)
            }
            else if(status == LoginState.LOGIN_FAILED){
                Toast.makeText(requireContext(), "Invalid Credentials. Please try again.", Toast.LENGTH_SHORT).show()
            }
            // else we status == NOT_LOGGED_IN, which means stay at login page, which is anyways the start of navgraph
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}