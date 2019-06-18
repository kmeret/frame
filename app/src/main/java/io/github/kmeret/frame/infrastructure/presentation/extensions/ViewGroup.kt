package io.github.kmeret.frame.infrastructure.presentation.extensions

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleableRes

fun ViewGroup.inflateViewAsRoot(layoutResId: Int): View = View.inflate(context, layoutResId, this)

fun ViewGroup.inflateView(layoutResId: Int): View = LayoutInflater.from(context).inflate(layoutResId, this, false)

fun ViewGroup.obtainAttrs(
    attrs: AttributeSet?, @StyleableRes attrsId: IntArray,
    callback: (array: TypedArray) -> Unit
) {
    val array = context.obtainStyledAttributes(attrs, attrsId)
    callback.invoke(array)
    array.recycle()
}