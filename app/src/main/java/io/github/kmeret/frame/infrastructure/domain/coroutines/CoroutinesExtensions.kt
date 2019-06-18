package io.github.kmeret.frame.infrastructure.domain.coroutines

import kotlinx.coroutines.*

fun <T> (suspend () -> T).execute(result: (Result) -> Unit): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        result.invoke(Result.Loading(true))
        try {
            val data = withContext(Dispatchers.IO) { invoke() }
            result.invoke(Result.Success<T>(data))
        } catch (th: Throwable) {
            result.invoke(Result.Error(th))
        } finally {
            result.invoke(Result.Loading(false))
        }
    }
}