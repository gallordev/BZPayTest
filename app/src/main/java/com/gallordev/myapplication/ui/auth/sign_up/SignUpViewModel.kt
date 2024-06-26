package com.gallordev.myapplication.ui.auth.sign_up

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
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _resource = MutableLiveData<Resource<String>>()
    val resource: LiveData<Resource<String>>
        get() = _resource

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (!areValidCredentials(email, password, confirmPassword)) return
        viewModelScope.launch {
            authRepository.signUp(email, password).collect {
                _resource.postValue(it)
            }
        }
    }

    private fun areValidCredentials(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        var errorMessage = ""
        when {
            !email.isValidEmail() -> errorMessage += "Please provide a valid email"
            password.isEmpty() -> errorMessage += "Please provide a valid password"
            password != confirmPassword -> errorMessage += "Passwords fields does not match"
        }
        if (errorMessage.isNotBlank()) {
            _resource.value = Resource.Error(errorMessage)
            return false
        }
        return true
    }

}