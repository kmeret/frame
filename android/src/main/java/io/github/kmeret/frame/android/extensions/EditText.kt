package io.github.kmeret.frame.android.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(action: (text: String, length: Int) -> Unit) {
    val length = text.length
    addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s == null) return

            val fromUser = Math.abs(count - before) == 1

            if (fromUser) action.invoke(s.toString(), length)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
    })
}



