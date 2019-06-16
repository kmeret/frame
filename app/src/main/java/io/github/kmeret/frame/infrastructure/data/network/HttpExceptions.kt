package io.github.kmeret.frame.infrastructure.data.network

import java.io.IOException


class NetworkException : IOException()

class TimeoutException : IOException()

open class ServerException(
    val httpCode: Int,
    val code: String? = null,
    val description: String? = null
) : IOException() {
    override fun toString(): String {
        return "httpCode:$httpCode code:$code description:$description"
    }
}

class AuthException : ServerException(HttpCodes.NOT_AUTHORIZED)

class BadRequestException : ServerException(HttpCodes.BAD_REQUEST)

class NotFoundException : ServerException(HttpCodes.NOT_FOUND)