package io.github.kmeret.frame.infrastructure.domain.coroutines

sealed class Result {
    data class Loading(val isLoading: Boolean) : Result()
    data class Success<T>(val data: T) : Result()
    data class Error(val throwable: Throwable) : Result()
}

