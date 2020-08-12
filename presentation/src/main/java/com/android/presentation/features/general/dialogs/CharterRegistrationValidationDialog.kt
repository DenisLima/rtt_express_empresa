package com.android.presentation.features.general.dialogs

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.android.presentation.R

class CharterRegistrationValidationDialog(context: Context) {
    private val dialog = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_charter_registration_validation)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        //progressLoading.tint(R.color.green_bdd753)
    }
}