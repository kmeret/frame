package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.content.Context
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Context.toFloat(dp: Int) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this.resources.displayMetrics)

fun Context.resolveFragmentManager(): FragmentManager {
    return when (this) {
        is AppCompatActivity -> supportFragmentManager
        is Fragment -> childFragmentManager
        else -> throw Exception("Cannot resolve!")
    }
}
