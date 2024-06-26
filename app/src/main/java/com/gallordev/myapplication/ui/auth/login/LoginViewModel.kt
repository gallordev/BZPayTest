package com.gallordev.myapplication.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallordev.myapplication.repository.AuthRepository
import com.gallordev.myapplication.utils.Extensions.isValidEmail
import com.gallordev.myapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _resource = MutableLiveData<Resource<String>>()
    val resource: LiveData<Resource<String>>
        get() = _resource

    fun login(email: String, password: String) {
        if (!validateCredentials(email, password)) return
        viewModelScope.launch {
            authRepository.signIn(email, password).collect {
                _resource.postValue(it)
            }
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        if (!email.isValidEmail() || password.isEmpty()) {
            _resource.value = Resource.Error("Pleas provide valid credentials")
            return false
        }
        return true
    }

}