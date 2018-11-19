package io.github.kmeret.frame.network

sealed class NetworkException(error: String = "") : Exception(error)
class ApiException(error: String = "") : NetworkException(error)
object UnknownResponseCodeException : NetworkException()
object NotAuthException : NetworkException()
object NoConnectionException : NetworkException()
