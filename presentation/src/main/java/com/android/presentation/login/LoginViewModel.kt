package com.android.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.splash.SplashUseCases
import com.android.domain.login.LoginUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(
    private val loginUseCases: LoginUseCases,
    private val splashUseCases: SplashUseCases
) : BaseViewModel() {

    private val emailLv = MutableLiveData(false)

    private val passwordLv = MutableLiveData(false)

    private val navigateToHomeLv = MutableLiveData<Unit>()
    fun getNavigateToHome(): LiveData<Unit> = navigateToHomeLv

    private val enableButtonLv = MutableLiveData<Boolean>()
    fun getEnableButton(): LiveData<Boolean> = enableButtonLv

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessageLv(): LiveData<String> = errorMessageLv

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = isLoadingLv

    fun checkLogin(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLv.postValue(true)
            try {
                loginUseCases.login(email, password)
                isLoadingLv.postValue(false)
                navigateToHomeLv.postValue(Unit)
            } catch (e: Exception) {
                isLoadingLv.postValue(false)
                errorMessageLv.postValue(e.message)
            }
        }

    }

    fun afterEmailChange(s: CharSequence) {
        emailLv.value = s.length > 0
        checkButton()
    }

    fun afterPasswordChange(s: CharSequence) {
        passwordLv.value = s.length > 0
        checkButton()
    }

    private fun checkButton() {
        if (emailLv.value!! &&
            passwordLv.value!!
        ) {
            enableButtonLv.postValue(true)
        } else {
            enableButtonLv.postValue(false)
        }
    }

    fun checkPrefs() {
        splashUseCases.getUserLogged()
            .subscribeOnUi {
                val user = it
            }
    }

}