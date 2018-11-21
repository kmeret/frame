package io.github.kmeret.frame.network

sealed class NetworkException(error: String = "") : Exception(error)
class ApiMessageException(error: String = "") : NetworkException(error)
object ApiException : NetworkException()
object UnknownResponseCodeException : NetworkException()
object NotAuthException : NetworkException()
object NoConnectionException : NetworkException()