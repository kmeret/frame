package io.github.kmeret.frame.infrastructure.domain.coroutines

import kotlinx.coroutines.*

abstract class CoroutineUseCase<T> {

    abstract suspend fun asyncRequest(): T

    fun execute(
        isLoading: (Boolean) -> Unit,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Job {

        return CoroutineScope(Dispatchers.Main).launch {
            isLoading.invoke(true)
            try {
                val data = withContext(Dispatchers.IO) { asyncRequest() }
                onSuccess.invoke(data)
            } catch (th: Throwable) {
                onError.invoke(th)
            } finally {
                isLoading.invoke(false)
            }
        }
    }
}