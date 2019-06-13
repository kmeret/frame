package io.github.kmeret.frame.infrastructure.application.lifecycle

import androidx.lifecycle.MutableLiveData
import java.util.concurrent.ConcurrentLinkedQueue

class LiveCommand<T> : MutableLiveData<ConcurrentLinkedQueue<T>>() {
    fun onNext(value: T) {
        val commands = getValue() ?: ConcurrentLinkedQueue()
        commands.add(value)
        setValue(commands)
    }
}