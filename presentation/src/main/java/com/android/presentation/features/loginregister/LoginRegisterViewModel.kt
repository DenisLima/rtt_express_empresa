package com.android.presentation.features.loginregister

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.features.loginregister.LoginRegisterUseCases
import com.android.presentation.features.general.bases.BaseViewModel
import com.android.presentation.features.loginregister.models.PLoginRegisterModel
import com.google.android.material.textfield.TextInputEditText
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

    fun setTermAccepted(checked: Boolean) {
        acceptedTermLv = checked
        checkButton()
    }

    fun onRegister(loginRegisterModel: PLoginRegisterModel) {
        isLoadingLv.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (
                    loginRegisterUseCases.userRegister(
                        email = loginRegisterModel.email,
                        password = loginRegisterModel.password,
                        fullName = loginRegisterModel.fullName
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