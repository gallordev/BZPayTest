package com.gallordev.myapplication.repository

import com.gallordev.myapplication.model.User
import com.gallordev.myapplication.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val currentUserId: String
    val currentUser: Flow<User>

    suspend fun signUp(email: String, password: String): Flow<Resource<String>>
    suspend fun signIn(email: String, password: String): Flow<Resource<String>>
    suspend fun deleteAccount()
    suspend fun logout()

}