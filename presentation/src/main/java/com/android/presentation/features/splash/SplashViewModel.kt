package com.android.presentation.features.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.domain.features.splash.SplashUseCases
import com.android.presentation.features.general.bases.BaseViewModel

class SplashViewModel(
private val splashUseCases: SplashUseCases
): BaseViewModel() {

    private val navigateToHomeLv = MutableLiveData<Unit>()
    fun getNavigateToHome(): LiveData<Unit> = navigateToHomeLv

    private val navigateToLoginLv = MutableLiveData<Unit>()
    fun getNavigateToLogin(): LiveData<Unit> = navigateToLoginLv

    fun checkNavigate() {
        splashUseCases.isLoggedIn()
            .subscribeOnUi(
                onSuccess = {

                    if (it) {
                        navigateToHomeLv.value = Unit
                    } else {
                        navigateToLoginLv.value = Unit
                    }
                },
                onError = {
                    navigateToLoginLv.value = Unit
                    true}
            )
    }

}