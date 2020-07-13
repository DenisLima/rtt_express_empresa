package com.android.presentation.features.general.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.presentation.R

class AlertDialogFragment(
    private val title: String,
    private val description: String

): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(description)
            .setNeutralButton(getString(R.string.general_alert_dialog_ok),null)
            .create()

    }
}