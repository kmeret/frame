package io.github.kmeret.frame.demo.domain.stars

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.lifecycle.Event

abstract class UseCase <Request, Response> {

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Exception>()
    val empty = Event()
    val data = MutableLiveData<Response>()

    abstract fun execute(request: Request)

}
