package ru.sksbank.mrm.app.utils

import android.content.Context
import android.util.TypedValue

fun Context.spToPx(sp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics).toInt()