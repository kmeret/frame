package io.github.kmeret.frame.infrastructure.presentation.base.viewmodel

import androidx.lifecycle.MutableLiveData

abstract class LiveUseCase <Request, Response> {

    val data = MutableLiveData<Response>()
    val loading = MutableLiveData<Boolean>()
    val empty = DataEvent<Boolean>()
    val error = DataEvent<Exception>()

    abstract fun execute(request: Request)

}
