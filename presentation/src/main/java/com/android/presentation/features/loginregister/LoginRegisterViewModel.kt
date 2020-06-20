package com.android.presentation.features.loginregister

import androidx.lifecycle.viewModelScope
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.presentation.features.general.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRegisterViewModel(private val loginRegisterUseCases: LoginRegisterUseCases):BaseViewModel()
{
    fun onRegister(){
    viewModelScope.launch(Dispatchers.IO) {
        loginRegisterUseCases.userRegister("walter.ohri@gmail.com", "123")
    }
    }
}