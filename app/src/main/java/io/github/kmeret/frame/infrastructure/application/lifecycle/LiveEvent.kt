package io.github.kmeret.frame.infrastructure.application.lifecycle

import androidx.lifecycle.MutableLiveData
import java.util.concurrent.ConcurrentLinkedQueue

class LiveEvent : MutableLiveData<ConcurrentLinkedQueue<Unit>>() {
    fun invoke() {
        val commands = value ?: ConcurrentLinkedQueue()
        commands.add(Unit)
        value = commands
    }
}