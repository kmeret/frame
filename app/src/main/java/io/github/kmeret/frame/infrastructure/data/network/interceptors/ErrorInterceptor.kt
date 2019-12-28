package io.github.kmeret.frame.infrastructure.data.network.interceptors

import android.util.Log
import io.github.kmeret.frame.BuildConfig
import io.github.kmeret.frame.infrastructure.data.network.*
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response = try {
            chain.proceed(chain.request())
        } catch (th: IOException) {
            if (BuildConfig.DEBUG) {
                Log.d("OkHttp", th.message.toString())
            }

            when (th) {
                is SocketTimeoutException -> throw TimeoutException()
                else -> throw NetworkException()
            }
        }

        if (response.isSuccessful) return response

        val httpCode = response.code()
        val body = response.body()?.string()
        val serverException = ServerException(httpCode, description = body)

        if (BuildConfig.DEBUG) {
            Log.d("OkHttp", serverException.toString())
        }
        throw map(serverException)
    }

    private fun map(serverException: ServerException): Throwable {
        return when (serverException.httpCode) {
            HttpCodes.NOT_AUTHORIZED -> AuthException()
            HttpCodes.BAD_REQUEST -> BadRequestException()
            HttpCodes.NOT_FOUND -> NotFoundException()
            else -> serverException
        }
    }
}