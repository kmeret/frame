package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.content.Context
import android.util.TypedValue

fun Context.spToPx(sp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics).toInt()