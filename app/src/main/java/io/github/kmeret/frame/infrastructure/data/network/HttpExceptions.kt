package io.github.kmeret.frame.infrastructure.data.network


class NetworkException : Exception()

class TimeoutException : Exception()

open class ServerException(
    val httpCode: Int,
    val code: String? = null,
    val description: String? = null
) : Exception() {
    override fun toString(): String {
        return "httpCode:$httpCode code:$code description:$description"
    }
}

class AuthException : ServerException(HttpCodes.NOT_AUTHORIZED)

class BadRequestException : ServerException(HttpCodes.BAD_REQUEST)

class NotFoundException : ServerException(HttpCodes.NOT_FOUND)

