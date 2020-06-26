package com.android.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.login.LoginUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(private val loginUseCases: LoginUseCases) : BaseViewModel() {

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessageLv(): LiveData<String> = errorMessageLv

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = isLoadingLv

    private fun checkLogin(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLv.postValue(true)
            try {
                loginUseCases.login(email, password)
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                isLoadingLv.postValue(false)
                errorMessageLv.postValue(e.message)
            }
        }

    }

    fun assembleLogin() {
        checkLogin("denis@denis.com", "123")
    }

}