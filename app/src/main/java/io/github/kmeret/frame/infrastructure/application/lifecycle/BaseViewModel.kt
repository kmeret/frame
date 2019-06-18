package io.github.kmeret.frame.infrastructure.application.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.infrastructure.domain.coroutines.CompositeJob
import io.github.kmeret.frame.infrastructure.domain.coroutines.Result
import io.github.kmeret.frame.infrastructure.domain.coroutines.execute
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    private val compositeJob = CompositeJob()

    val commands = LiveCommand<VMCommand>()
    val isLoading = MutableLiveData<Boolean>()
    val errors = LiveCommand<Throwable>()

    override fun onCleared() {
        super.onCleared()
        compositeJob.clear()
    }

    abstract fun onBackPressed()

    abstract fun onInit()

    protected fun <T> (suspend () -> T).subscribe(result: (Result) -> Unit) {
        safeSubscribe {
            execute(result)
        }
    }

    protected fun safeSubscribe(block: () -> Job) {
        compositeJob.add(block.invoke())
    }

    protected fun <T> handleResult(result: Result, onSuccess: (data: T) -> Unit) {
        when(result) {
            is Result.Loading -> isLoading.onNext(result.isLoading)
            is Result.Success<*> -> onSuccess.invoke(result.data as T)
            is Result.Error -> errors.onNext(result.throwable)
        }
    }

}