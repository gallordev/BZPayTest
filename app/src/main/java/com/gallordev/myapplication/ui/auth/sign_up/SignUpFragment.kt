package com.gallordev.myapplication.ui.auth.sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gallordev.myapplication.R
import com.gallordev.myapplication.databinding.FragmentSignUpBinding
import com.gallordev.myapplication.utils.BaseFragment
import com.gallordev.myapplication.utils.Extensions.getText
import com.gallordev.myapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resource.observe(viewLifecycleOwner) {
            val result = it ?: return@observe
            when (result) {
                is Resource.Error -> {
                    binding.btnSignUp.isEnabled = true
                    showErrorMessage(result.message)
                }
                is Resource.Loading -> {
                    binding.btnSignUp.isEnabled = false
                }
                is Resource.Success -> {
                    binding.btnSignUp.isEnabled = true
                    findNavController().popBackStack(R.id.homeFragment, true)
                }
            }
        }
        with(binding) {
            btnSignUp.setOnClickListener {
                viewModel.signUp(
                    textFieldEmail.getText(),
                    textFieldPassword.getText(),
                    textFieldConfirmPassword.getText()
                )
            }
        }
    }

}