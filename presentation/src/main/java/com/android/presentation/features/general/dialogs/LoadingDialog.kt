package com.android.presentation.features.general.dialogs


import android.app.Dialog
import android.content.Context
import android.view.Window
import com.android.presentation.R
import kotlinx.android.synthetic.main.dialog_loading.*

class LoadingDialog(context: Context) {
    private val dialog = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        //progressLoading.tint(R.color.green_bdd753)
    }

    /**
     * Shows the loading dialog.
     */
    fun show() {
        with(dialog) {
            if (!isShowing) {
                show()
            }
        }
    }

    /**
     * Shows the loading dialog with message.
     */
    fun show(customMessage: String) {
        with(dialog) {
            if (!isShowing) {
                txtProgressMessage.text = customMessage
                show()
            }
        }
    }

    /**
     * Hides the loading dialog.
     */
    fun hide() {
        if (dialog.isShowing)
            dialog.dismiss()
    }

    /**
     * Returns if it is showing the loading dialog.
     */
    fun isShowing(): Boolean {
        return dialog.isShowing
    }
}