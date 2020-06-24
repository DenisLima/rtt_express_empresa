package com.android.presentation.features.loginregister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginRegisterViewModel(
    private val loginRegisterUseCases: LoginRegisterUseCases
) :  BaseViewModel() {

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessageLv(): LiveData<String> = errorMessageLv

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = isLoadingLv

    fun onRegister() {
        isLoadingLv.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                loginRegisterUseCases.userRegister(
                    email = "denis33@denis.com",
                    password = "123"
                )
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                errorMessageLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }

        }
    }
}