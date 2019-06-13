package io.github.kmeret.frame.android.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat

fun ImageView.vectorColor(res: Int) =
        setColorFilter(ContextCompat.getColor(context, res))

fun ImageView.setImageVector(resId: Int) =
        setImageDrawable(ContextCompat.getDrawable(context, resId))
