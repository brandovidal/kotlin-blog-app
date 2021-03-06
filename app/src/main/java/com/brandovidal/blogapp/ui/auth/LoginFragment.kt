package com.brandovidal.blogapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.brandovidal.blogapp.R
import com.brandovidal.blogapp.core.Resource
import com.brandovidal.blogapp.data.remote.auth.LoginDataSource
import com.brandovidal.blogapp.databinding.FragmentLoginBinding
import com.brandovidal.blogapp.domain.auth.LoginRepoImpl
import com.brandovidal.blogapp.presentation.auth.LoginScreenViewModel
import com.brandovidal.blogapp.presentation.auth.LoginScreenViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<LoginScreenViewModel> {
        LoginScreenViewModelFactory(
            LoginRepoImpl(
                LoginDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
        goToSignUpPage()
    }

    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
        }
    }

    private fun doLogin() {
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun goToSignUpPage() {
        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()) {
            binding.editTextEmail.error = "Email is empty"
            return
        }
        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is empty"
            return
        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false

                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)

                    Toast.makeText(
                        requireContext(),
                        "Welcome ${result.data?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignin.isEnabled = true

                    Toast.makeText(
                        requireContext(),
                        "Error ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}