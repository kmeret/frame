package io.github.kmeret.frame.lifecycle

import androidx.annotation.MainThread

class Event : DataEvent<Void>() {

    @MainThread
    operator fun invoke() {
        value = null
    }

}
