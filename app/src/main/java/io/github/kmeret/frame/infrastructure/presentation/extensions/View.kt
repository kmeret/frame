package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.view.View

fun View.onFocus(action: (hasFocus: Boolean) -> Unit) =
        setOnFocusChangeListener { _, hasFocus ->
            action.invoke(hasFocus)
        }

fun View.onClick(action: () -> Unit) =
        setOnClickListener {
            action.invoke()
        }

fun View.onLongClick(action: () -> Unit) =
        setOnLongClickListener onClick@{
            action.invoke()
            return@onClick true
        }

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

inline var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

inline var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }