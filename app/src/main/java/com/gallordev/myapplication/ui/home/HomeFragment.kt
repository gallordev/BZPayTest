package com.gallordev.myapplication.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gallordev.myapplication.R
import com.gallordev.myapplication.databinding.FragmentHomeBinding
import com.gallordev.myapplication.ui.auth.AuthStatusViewModel
import com.gallordev.myapplication.ui.student.StudentAdapter
import com.gallordev.myapplication.utils.BaseFragment
import com.gallordev.myapplication.utils.SwipeHelper.setItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val authStatusViewModel: AuthStatusViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var studentAdapter: StudentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authStatusViewModel.currentUser.observe(viewLifecycleOwner) {
            val user = it ?: return@observe
            if (user.id.isBlank()) {
                findNavController().navigate(R.id.loginFragment)
            }
        }

        studentAdapter = StudentAdapter(
            editCallback = {
                findNavController()
                    .navigate(
                        HomeFragmentDirections.actionHomeFragmentToStudentFormFragment(it.id)
                    )
            },
            deleteCallback = {
                homeViewModel.deleteStudent(it)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = studentAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        homeViewModel.students.observe(viewLifecycleOwner) {
            val students = it ?: return@observe
            studentAdapter.submitList(students)
            setItemTouchHelper(requireContext(), binding.recyclerView, studentAdapter.currentList.size)
        }

    }


}