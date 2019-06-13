package io.github.kmeret.frame.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, callback: (T) -> Unit) =
        observe(owner, Observer { if (it == null) return@Observer; callback.invoke(it) })