package io.github.kmeret.frame.infrastructure.domain.coroutines

import io.github.kmeret.frame.infrastructure.domain.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun <T> (suspend () -> T).execute(result: (Result) -> Unit): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        result.invoke(Result.Loading(true))
        try {
            val data = withContext(Dispatchers.IO) { invoke() }
            result.invoke(Result.Success(data))
        } catch (th: Throwable) {
            result.invoke(Result.Error(th))
        } finally {
            result.invoke(Result.Loading(false))
        }
    }
}

fun <T, R> (suspend (R) -> T).execute(args: R, context: CoroutineContext = Dispatchers.IO, result: (Result) -> Unit): Job {
    return CoroutineScope(Dispatchers.Main).launch {
        result.invoke(Result.Loading(true))
        try {
            val data = withContext(Dispatchers.IO) { invoke(args) }
            result.invoke(Result.Success(data))
        } catch (th: Throwable) {
            result.invoke(Result.Error(th))
        } finally {
            result.invoke(Result.Loading(false))
        }
    }
}