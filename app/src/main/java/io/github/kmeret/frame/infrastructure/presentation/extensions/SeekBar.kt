package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar

fun AppCompatSeekBar.onProgressChangedFromUser(body: (progress: Int) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekArc: SeekBar, progress: Int, fromUser: Boolean) {
            if (fromUser) body(progress)
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {}
        override fun onStopTrackingTouch(p0: SeekBar?) {}
    })
}
