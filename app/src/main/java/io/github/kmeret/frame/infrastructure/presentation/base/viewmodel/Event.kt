package io.github.kmeret.frame.infrastructure.presentation.base.viewmodel

import androidx.annotation.MainThread

class Event : DataEvent<Void>() {

    @MainThread
    operator fun invoke() {
        value = null
    }

}
