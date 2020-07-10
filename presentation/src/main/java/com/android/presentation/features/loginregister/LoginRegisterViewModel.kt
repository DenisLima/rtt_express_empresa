package com.android.presentation.features.loginregister

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginRegisterViewModel(
    private val loginRegisterUseCases: LoginRegisterUseCases
) : BaseViewModel() {

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessageLv(): LiveData<String> = errorMessageLv

    private val isLoadingLv = MutableLiveData<Boolean>()
    fun isLoading(): LiveData<Boolean> = isLoadingLv

    private val userNameLv = MutableLiveData(false)

    private val emailLv = MutableLiveData(false)
    fun getEmail(): LiveData<Boolean> = emailLv

    private val passwordlv = MutableLiveData(false)

    private val passwordConfirmLv = MutableLiveData(false)
    fun getPasswordConfirm():LiveData<Boolean> = passwordConfirmLv

    private var acceptedTermLv: Boolean = false

    private val navigateToLoginLv = MutableLiveData(false)
    fun getNavigateToLogin(): LiveData<Boolean> = navigateToLoginLv

    private val enableButtonLv = MutableLiveData<Boolean>()
    fun getEnableButton(): LiveData<Boolean> = enableButtonLv

    private val linkClickedUrlLv = MutableLiveData<Unit>()
    fun getLinkCLickedUrl(): LiveData<Unit> = linkClickedUrlLv

    private var passwordFirst: String = ""

    fun setTermAccepted(checked: Boolean) {
        acceptedTermLv = checked
        checkButton()
    }

    fun onRegister(fullName: String, email: String, password: String) {
        isLoadingLv.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (
                    loginRegisterUseCases.userRegister(
                        email = email,
                        password = password,
                        fullName = fullName
                    ).status
                ) {
                    navigateToLoginLv.postValue(true)
                } else {
                    errorMessageLv.postValue("")
                }
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

        checkButton()
        emailLv.value = isValidEmail(s.toString())


    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun afterPasswordChange(s: CharSequence) {
        passwordlv.value = s.length > 0
        passwordFirst = s.toString()
        checkButton()
    }

    fun afterConfirmPasswordChange(s: CharSequence) {

        passwordConfirmLv.value = s.toString().equals(passwordFirst)
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