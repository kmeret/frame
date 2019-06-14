package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
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

fun EditText.onDoneClicked(action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) action.invoke()
        return@setOnEditorActionListener false
    }
}

fun String.toEditable() = SpannableStringBuilder(this)