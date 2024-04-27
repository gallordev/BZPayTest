package com.gallordev.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val age: Int = 0
)
