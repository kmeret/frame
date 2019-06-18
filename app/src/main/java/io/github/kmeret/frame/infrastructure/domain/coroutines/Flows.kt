package io.github.kmeret.frame.infrastructure.domain.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
fun <T> Flow<T>.execute(result: (Result) -> Unit) = flow<T> {
    result.invoke(Result.Loading(true))
    try {
        collect {
            result.invoke(Result.Success(it))
        }
    } catch (th: Throwable) {
        result.invoke(Result.Error(th))
    }

    result.invoke(Result.Loading(false))
}