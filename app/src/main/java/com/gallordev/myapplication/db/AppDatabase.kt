package com.gallordev.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gallordev.myapplication.db.dao.StudentDao
import com.gallordev.myapplication.model.Student

@Database(entities = [Student::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDao

}