package com.android.presentation.features.loginregister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import com.android.presentation.features.loginregister.models.PLoginRegisterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginRegisterViewModel(
    private val loginRegisterUseCases: LoginRegisterUseCases
) : BaseViewModel() {

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessageLv(): LiveData<String> = errorMessageLv

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = isLoadingLv

    private val userNameLv = MutableLiveData<String>()
    fun getUserName(): LiveData<String> = userNameLv

    fun onRegister(loginRegisterModel: PLoginRegisterModel) {
        isLoadingLv.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                loginRegisterUseCases.userRegister(
                    email = loginRegisterModel.email,
                    password = loginRegisterModel.password,
                    fullName = loginRegisterModel.fullName
                )
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                errorMessageLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }

        }
    }

    fun afterNameChange(s: CharSequence) {
        userNameLv.value = s.toString()
    }

    fun loginRegisterModel(fullName: String, email: String, password: String) {

        onRegister(
            PLoginRegisterModel(
                fullName,
                email,
                password
            )
        )
    }
}