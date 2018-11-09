package io.github.kmeret.frame.android.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
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

@SuppressLint("MissingPermission")
fun Context.isOnline(): Boolean {
    val manager = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    val network = manager.activeNetworkInfo
    return (network != null && network.isAvailable && network.isConnected)
}
