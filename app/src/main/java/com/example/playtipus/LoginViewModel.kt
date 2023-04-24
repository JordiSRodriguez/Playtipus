package com.example.playtipus

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

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

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable : LiveData<Boolean> = _registerEnable

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = emailValido(email) && passwordValida(password)
    }

    private fun emailValido(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun passwordValida(password: String): Boolean = password.length >= 6

}