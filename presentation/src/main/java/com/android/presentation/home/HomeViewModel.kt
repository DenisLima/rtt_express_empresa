package com.android.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.domain.features.session.SessionUseCases
import com.android.domain.features.session.models.User
import com.android.presentation.features.general.bases.BaseViewModel
import java.lang.Exception

class HomeViewModel(
    private val sessionUseCases: SessionUseCases
): BaseViewModel() {

    private val userInfoLv = MutableLiveData<User>()
    fun getUserInfo(): LiveData<User> = userInfoLv

    private val errorMessageLv = MutableLiveData<String>()
    fun getErrorMessage(): LiveData<String> = errorMessageLv

    fun getUser() {
        sessionUseCases.getUserLogged()
            .subscribeOnUi(
                onSuccess = {
                    userInfoLv.value = it
                },
                onError = {
                    errorMessageLv.value = it.message
                    false
                }
            )
    }

}