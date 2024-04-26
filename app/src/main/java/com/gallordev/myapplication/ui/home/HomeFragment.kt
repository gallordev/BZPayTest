package com.gallordev.myapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gallordev.myapplication.R
import com.gallordev.myapplication.databinding.FragmentHomeBinding
import com.gallordev.myapplication.ui.auth.AuthStatusViewModel
import com.gallordev.myapplication.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: AuthStatusViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentUser.observe(viewLifecycleOwner) {
            val user = it ?: return@observe
            if (user.id.isBlank()) {
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }

}