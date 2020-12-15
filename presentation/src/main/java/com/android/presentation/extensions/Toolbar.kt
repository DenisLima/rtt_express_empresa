package com.android.presentation.extensions

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import com.android.presentation.R

fun Toolbar.changeToolbarFont(){
    for (i in 0 until childCount) {
        val view = getChildAt(i)
        if (view is TextView && view.text == title) {
            view.textSize = 18F
            view.typeface = ResourcesCompat.getFont(context, R.font.montserrat_regular)
            break
        }
    }
}