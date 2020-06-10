package com.android.presentation.features.jobslist.main

import androidx.lifecycle.MutableLiveData
import com.android.presentation.features.general.BaseViewModel

class MainViewModel: BaseViewModel() {
    val showAndroidJobsLiveData = MutableLiveData<Boolean>()
    val outAppLiveData = MutableLiveData<Boolean>()

    fun onShowAndroidJobsRequire() {
        showAndroidJobsLiveData.postValue(true)
    }

    fun onOutAppLiveData() {
        outAppLiveData.postValue(true)
    }
}