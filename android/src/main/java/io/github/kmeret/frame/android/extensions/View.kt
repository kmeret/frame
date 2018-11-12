package io.github.kmeret.frame.android.extensions

import android.view.*
import androidx.core.view.GestureDetectorCompat

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

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

fun GestureDetectorCompat.onDoubleTap(action: () -> Unit) =
        setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                action.invoke()
                return true
            }

            override fun onDoubleTapEvent(e: MotionEvent?) = false
            override fun onSingleTapConfirmed(e: MotionEvent?) = false
        })

fun ViewGroup.inflate(layoutRes: Int, attach: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attach)
