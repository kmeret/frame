package io.github.kmeret.frame.android.extensions

import android.text.SpannableStringBuilder

fun String.toEditable() = SpannableStringBuilder(this)

fun Int.padStart(length: Int, padChar: Char): String {
    return this.toString().padStart(length, padChar)
}