package com.gallordev.myapplication.repository

import com.gallordev.myapplication.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    val students: Flow<List<Student>>
    suspend fun getStudentById(id:Int): Flow<Student?>
    suspend fun insertStudent(student: Student)
    suspend fun updateStudent(student: Student)
    suspend fun deleteStudent(student: Student)
}