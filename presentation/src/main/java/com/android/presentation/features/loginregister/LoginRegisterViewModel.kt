package com.android.presentation.features.loginregister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.presentation.features.general.bases.BaseViewModel
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

    private val userNameLv = MutableLiveData(false)

    private val emailLv = MutableLiveData(false)

    private val passwordlv = MutableLiveData(false)

    private val passwordConfirmLv = MutableLiveData(false)

    private var acceptedTermLv: Boolean = false

    private val navigateToLoginLv = MutableLiveData(false)
    fun getNavigateToLogin(): LiveData<Boolean> = navigateToLoginLv

    private val enableButtonLv = MutableLiveData<Boolean>()
    fun getEnableButton(): LiveData<Boolean> = enableButtonLv

    private val linkClickedUrlLv = MutableLiveData<Unit>()
    fun getLinkCLickedUrl(): LiveData<Unit> = linkClickedUrlLv

    fun setTermAccepted(checked: Boolean) {
        acceptedTermLv = checked
        checkButton()
    }

    fun onRegister(fullName: String, email: String, password: String) {
        isLoadingLv.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                loginRegisterUseCases.userRegister(
                    email = email,
                    password = password,
                    fullName = fullName
                )
                navigateToLoginLv.postValue(true)
                isLoadingLv.postValue(false)
            } catch (e: Exception) {
                errorMessageLv.postValue(e.message)
                isLoadingLv.postValue(false)
            }

        }
    }

    fun afterNameChange(s: CharSequence) {
        userNameLv.value = s.length > 0
        checkButton()
    }

    fun afterEmailChange(s: CharSequence) {
        emailLv.value = s.length > 0
        checkButton()
    }

    fun afterPasswordChange(s: CharSequence) {
        passwordlv.value = s.length > 0
        checkButton()
    }

    fun afterConfirmPasswordChange(s: CharSequence) {
        passwordConfirmLv.value = s.length > 0
        checkButton()
    }

    private fun checkButton() {
        if (userNameLv.value!! &&
            emailLv.value!! &&
            passwordlv.value!! &&
            passwordConfirmLv.value!! &&
            acceptedTermLv
        ) {
            enableButtonLv.postValue(true)
        } else {
            enableButtonLv.postValue(false)
        }
    }

    fun onTermOfUserClicked() {
        linkClickedUrlLv.postValue(Unit)
    }

}