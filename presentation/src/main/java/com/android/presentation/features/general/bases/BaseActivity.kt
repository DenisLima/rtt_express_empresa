package com.android.presentation.features.general.bases

import android.view.MenuItem
import androidx.annotation.CallSuper
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

    fun setToolbarVisible(isVisible: Boolean) {
        if (isVisible)
            supportActionBar?.show()
        else
            supportActionBar?.hide()
    }

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setBackOnActionBarEnabled(isEnabled: Boolean) {
        supportActionBar?.apply {
            setHomeButtonEnabled(isEnabled)
            setDisplayHomeAsUpEnabled(isEnabled)
        }
    }
}