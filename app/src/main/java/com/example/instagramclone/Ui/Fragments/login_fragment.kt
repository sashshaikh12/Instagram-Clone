package com.example.instagramclone.Ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.instagramclone.R
import com.example.instagramclone.databinding.LoginFragmentBinding

class LoginFragment : Fragment(R.layout.login_fragment){
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = LoginFragmentBinding.bind(view)

        _binding!!.LoginButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_feedFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}