package com.gallordev.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallordev.myapplication.model.Student
import com.gallordev.myapplication.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    private val _students = MutableLiveData<List<Student>>(null)
    val students: LiveData<List<Student>>
        get() = _students

    init {
        viewModelScope.launch {
            studentRepository.students.collect {
                _students.value = it
            }
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.deleteStudent(student)
        }
    }

}