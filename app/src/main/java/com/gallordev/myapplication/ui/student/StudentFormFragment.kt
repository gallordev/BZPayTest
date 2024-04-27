package com.gallordev.myapplication.ui.student

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gallordev.myapplication.R
import com.gallordev.myapplication.databinding.FragmentStudentFormBinding
import com.gallordev.myapplication.model.Student
import com.gallordev.myapplication.utils.BaseFragment
import com.gallordev.myapplication.utils.Extensions.getText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentFormFragment :
    BaseFragment<FragmentStudentFormBinding>(FragmentStudentFormBinding::inflate) {

    private val studentViewModel: StudentViewModel by viewModels()
    private val args: StudentFormFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.studentId != -1) {
            studentViewModel.getStudentById(args.studentId)
            binding.btnSave.text = resources.getString(R.string.btn_update)
        }

        studentViewModel.student.observe(viewLifecycleOwner) {
            val student = it ?: return@observe
            loadStudentInfo(student)
        }

        studentViewModel.errorMessage.observe(viewLifecycleOwner) {
            val errorMessage = it ?: return@observe
            showErrorMessage(errorMessage, studentViewModel::cleanErrorMessage)
        }

        binding.btnSave.setOnClickListener {
            val result = if (args.studentId == -1) {
                studentViewModel.insertStudent(getStudentInfo())
            } else {
                val student = studentViewModel.student.value?.copy(age = getStudentInfo().age)
                    ?: return@setOnClickListener
                studentViewModel.updateStudent(student)
            }
            if (result) findNavController().popBackStack()
        }
    }

    private fun getStudentInfo(): Student {
        with(binding) {
            val name = textFieldStudentName.getText()
            val age = if (textFieldStudentAge.getText().isBlank()) 0 else {
                textFieldStudentAge.getText().toInt()
            }
            return Student(
                name = name,
                age = age
            )
        }
    }

    private fun loadStudentInfo(student: Student) {
        with(binding) {
            textFieldStudentName.editText?.setText(student.name)
            textFieldStudentName.isEnabled = false
            textFieldStudentAge.editText?.setText(student.age.toString())
        }
    }

}