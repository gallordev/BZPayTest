package com.gallordev.myapplication.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gallordev.myapplication.R
import com.gallordev.myapplication.databinding.FragmentLoginBinding
import com.gallordev.myapplication.utils.BaseFragment
import com.gallordev.myapplication.utils.Extensions.getText
import com.gallordev.myapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resource.observe(viewLifecycleOwner) {
            val result = it ?: return@observe
            when(result) {
                is Resource.Error -> {
                    binding.btnLogin.isEnabled = true
                    showErrorMessage(result.message)
                }
                is Resource.Loading -> {
                    binding.btnLogin.isEnabled = false
                }
                is Resource.Success -> {
                    binding.btnLogin.isEnabled = true
                }
            }
        }
        with(binding) {
            btnLogin.setOnClickListener {
                viewModel.login(
                    textFieldEmail.getText(),
                    textFieldPassword.getText()
                )
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
        }
    }

}