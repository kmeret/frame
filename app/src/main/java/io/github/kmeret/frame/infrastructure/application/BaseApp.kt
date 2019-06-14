package io.github.kmeret.frame.infrastructure.application

import android.app.Application
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.data.network.AuthException
import io.github.kmeret.frame.infrastructure.data.network.NetworkException
import io.github.kmeret.frame.infrastructure.data.network.ServerException
import io.github.kmeret.frame.infrastructure.data.network.TimeoutException
import okhttp3.logging.HttpLoggingInterceptor

abstract class BaseApp : Application() {

    companion object {
        const val DEFAULT_DELAY_MS = 1000L
        val HTTP_LOGGING_LEVEL = HttpLoggingInterceptor.Level.BASIC
        const val HTTP_CLIENT_TIMEOUT_SEC = 60L
    }

    abstract fun onInit()

    override fun onCreate() {
        super.onCreate()
        onInit()
    }

    fun mapError(exception: Throwable): String {
        with(applicationContext) {
            return when (exception) {
                is ServerException -> getString(R.string.api_exception)
                is AuthException -> getString(R.string.not_auth_exception)
                is TimeoutException -> getString(R.string.no_connection_exception)
                is NetworkException -> getString(R.string.network_exception)
                else -> getString(R.string.unknown_exception)
            }
        }
    }
}