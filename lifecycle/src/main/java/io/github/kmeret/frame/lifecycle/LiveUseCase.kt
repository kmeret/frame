package io.github.kmeret.frame.lifecycle

import androidx.lifecycle.MutableLiveData

abstract class LiveUseCase <Request, Response> {

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Exception>()
    val empty = Event()
    var data = MutableLiveData<Response>()

    abstract fun execute(request: Request)

}
