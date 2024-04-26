package com.gallordev.myapplication.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gallordev.myapplication.model.User
import com.gallordev.myapplication.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AuthStatusViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _currentUser = authRepository.currentUser.asLiveData(Dispatchers.Main)
    val currentUser: LiveData<User>
        get() = _currentUser

}