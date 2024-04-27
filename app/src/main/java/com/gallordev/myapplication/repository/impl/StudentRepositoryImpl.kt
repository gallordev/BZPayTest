package com.gallordev.myapplication.repository.impl

import com.gallordev.myapplication.db.dao.StudentDao
import com.gallordev.myapplication.model.Student
import com.gallordev.myapplication.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
): StudentRepository {
    override val students: Flow<List<Student>>
        get() = studentDao.getAllStudents()

    override suspend fun getStudentById(id: Int): Flow<Student?> {
        return studentDao.getStudentById(id)
    }

    override suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    override suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }

    override suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }
}