package com.android.presentation.features.general.bases

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.android.presentation.features.general.dialogs.LoadingDialog

open class BaseActivity: AppCompatActivity() {

    private val loadingDialog by lazy { LoadingDialog(this) }
    private val onDestroyLiveDataBag = mutableListOf<LiveData<*>>()

    open fun showLoading() {
        loadingDialog.show()
    }

    open fun showLoading(customMsg: String) {
        loadingDialog.show(customMsg)
    }

    open fun hideLoading() {
        loadingDialog.hide()
    }

    fun LiveData<*>.removeObserversOnDestroy() {
        onDestroyLiveDataBag.add(this)
    }
}