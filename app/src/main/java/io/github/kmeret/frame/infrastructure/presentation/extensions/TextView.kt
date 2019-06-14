package io.github.kmeret.frame.infrastructure.presentation.extensions

import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

fun AppCompatTextView.setColor(@ColorRes colorRes: Int) {
    setTextColor(ContextCompat.getColor(context, colorRes))
}