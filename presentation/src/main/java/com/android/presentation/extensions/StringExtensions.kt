package com.android.presentation.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

@Suppress("DEPRECATION")
fun String?.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}