package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.widget.CheckBox

fun CheckBox.isChecked(callback: (Boolean) -> Unit) {
    setOnCheckedChangeListener { _, isChecked -> callback.invoke(isChecked) }
}