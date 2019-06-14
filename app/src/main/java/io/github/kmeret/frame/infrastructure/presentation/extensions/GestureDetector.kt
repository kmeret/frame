package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

fun GestureDetectorCompat.onDoubleTap(action: () -> Unit) =
    setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            action.invoke()
            return true
        }

        override fun onDoubleTapEvent(e: MotionEvent?) = false
        override fun onSingleTapConfirmed(e: MotionEvent?) = false
    })