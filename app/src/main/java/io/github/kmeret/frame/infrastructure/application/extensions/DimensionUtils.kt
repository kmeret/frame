package io.github.kmeret.frame.infrastructure.application.extensions

import android.content.Context
import android.util.TypedValue

fun Context.spToPx(sp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)

fun Context.dpToPx(dp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)