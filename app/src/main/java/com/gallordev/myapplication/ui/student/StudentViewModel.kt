package com.gallordev.myapplication.ui.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallordev.myapplication.model.Student
import com.gallordev.myapplication.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _student = MutableLiveData<Student>(null)
    val student: LiveData<Student>
        get() = _student

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    fun validateStudentData(student: Student): Boolean {
        if (student.name.isBlank() || student.age == 0) {
            _errorMessage.value = "Please provide valid name or valid age"
            return false
        }
        return true
    }

    fun getStudentById(id: Int) {
        viewModelScope.launch {
            studentRepository.getStudentById(id).collect {
                _student.value = it
            }
        }
    }

    fun insertStudent(student: Student): Boolean {
        if (!validateStudentData(student)) return false
        viewModelScope.launch {
            studentRepository.insertStudent(student)
        }
        return true
    }

    fun updateStudent(student: Student): Boolean {
        if (!validateStudentData(student)) return false
        viewModelScope.launch {
            studentRepository.updateStudent(student)
        }
        return true
    }

    fun cleanErrorMessage() {
        _errorMessage.value = null
    }

}