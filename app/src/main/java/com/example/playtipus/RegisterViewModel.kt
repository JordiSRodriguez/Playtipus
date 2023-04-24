package com.example.playtipus

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _fullName = MutableLiveData<String>()
    val fullName : LiveData<String> = _fullName

    private val _userName = MutableLiveData<String>()
    val userName : LiveData<String> = _userName

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword : LiveData<String> = _confirmPassword


    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable : LiveData<Boolean> = _registerEnable

    fun onEmailChanged(email: String) {
        _email.value = email
        _registerEnable.value = emailValido(email)
    }

    fun onFullNameChanged(fullName: String) {
        _fullName.value = fullName
        _registerEnable.value = fullNameValido(fullName)
    }

    fun onUserNameChanged(userName: String) {
        _userName.value = userName
        _registerEnable.value = userNameValido(userName)
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
        _registerEnable.value = passwordValida(password)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
        _registerEnable.value = passwordValida(confirmPassword)
    }


    private fun userNameValido(userName: String): Boolean = userName.length > 3

    private fun fullNameValido(fullName: String): Boolean = fullName.isNotBlank()

    private fun emailValido(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun passwordValida(password: String): Boolean = password.length >= 6

}