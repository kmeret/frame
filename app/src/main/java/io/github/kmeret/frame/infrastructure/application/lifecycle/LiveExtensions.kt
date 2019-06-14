package io.github.kmeret.frame.infrastructure.application.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.ConcurrentLinkedQueue

fun <T> LiveData<T?>.observe(owner: LifecycleOwner, action: (T) -> Unit) {
    observe(owner, Observer { data -> data?.let { action.invoke(it) } })
}

fun <T> LiveCommand<T>.observeCommand(owner: LifecycleOwner, action: (T) -> Unit) {
    observe(owner, Observer<ConcurrentLinkedQueue<T>> { commands ->
        if (commands == null) return@Observer
        var command: T?
        do {
            command = commands.poll()
            if (command != null) {
                action.invoke(command)
            }
        } while (command != null)
    })
}

fun <T> MutableLiveData<T>.onNext(data: T) {
    this.value = data
}

fun <T> MutableLiveData<T>.get(): T? {
    return this.value
}