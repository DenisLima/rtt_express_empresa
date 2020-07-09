package com.android.presentation.extensions

import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.MotionEvent
import android.widget.TextView

fun TextView.setOnLinkClicked(action: (linkUrl: String) -> Unit) {
    movementMethod = object : LinkMovementMethod() {
        override fun onTouchEvent(
            widget: TextView,
            buffer: Spannable,
            event: MotionEvent
        ): Boolean {
            if (event.action != MotionEvent.ACTION_UP)
                return super.onTouchEvent(widget, buffer, event)

            val x = event.x.toInt() - widget.totalPaddingLeft + widget.scrollX
            val y = event.y.toInt() - widget.totalPaddingTop + widget.scrollY

            val link = with(widget.layout) {
                val line = layout.getLineForVertical(y)
                val off = layout.getOffsetForHorizontal(line, x.toFloat())

                buffer.getSpans(off, off, URLSpan::class.java)
            }

            if (link.isNotEmpty()) {
                action(link[0].url)
            }
            return true
        }
    }
}