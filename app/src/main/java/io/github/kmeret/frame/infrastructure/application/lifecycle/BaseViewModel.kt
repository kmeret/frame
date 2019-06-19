package io.github.kmeret.frame.infrastructure.application.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.domain.model.auth.AuthCommand
import io.github.kmeret.frame.infrastructure.data.network.AuthException
import io.github.kmeret.frame.infrastructure.domain.Result
import io.github.kmeret.frame.infrastructure.domain.coroutines.CompositeJob
import io.github.kmeret.frame.infrastructure.domain.coroutines.execute
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    private val compositeJob = CompositeJob()

    val commands = LiveCommand<VMCommand>()
    val isLoading = MutableLiveData<Boolean>()
    val errors = LiveCommand<Throwable>()

    abstract fun onInit()

    abstract fun onBackPressed()

    override fun onCleared() {
        super.onCleared()
        compositeJob.clear()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T> (suspend () -> T).subscribe(onSuccess: (data: T) -> Unit) {
        safeSubscribe {
            execute { result ->
                when(result) {
                    is Result.Loading -> isLoading.onNext(result.isLoading)
                    is Result.Success<*> -> onSuccess.invoke(result.data as T)
                    is Result.Error -> handleError(result.throwable)
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T, R> (suspend (R) -> T).subscribe(args: R, onSuccess: (data: T) -> Unit) {
        safeSubscribe {
            execute(args) { result ->
                when(result) {
                    is Result.Loading -> isLoading.onNext(result.isLoading)
                    is Result.Success<*> -> onSuccess.invoke(result.data as T)
                    is Result.Error -> handleError(result.throwable)
                }
            }
        }
    }

    private fun handleError(th: Throwable, handler: ((Throwable) -> (Unit))) {
        if (th is AuthException) commands.onNext(AuthCommand.SignOut)
        handler.invoke(th)
    }

    private fun handleError(th: Throwable) = handleError(th) { errors.onNext(th) }

    private fun safeSubscribe(block: () -> Job) {
        compositeJob.add(block.invoke())
    }

}