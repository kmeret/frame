package io.github.kmeret.frame.infrastructure.application

import android.app.Application
import io.github.kmeret.frame.R
import io.github.kmeret.frame.infrastructure.data.network.*

abstract class BaseApp : Application() {

    companion object {
        const val DEFAULT_DELAY_MS = 1000L
    }

    abstract fun onInit()

    override fun onCreate() {
        super.onCreate()
        onInit()
    }

    fun mapError(exception: Throwable): String {
        with(applicationContext) {
            return when (exception) {
                is ApiMessageException -> exception.message ?: getString(R.string.api_exception)
                is ApiException -> getString(R.string.api_exception)
                is UnknownResponseCodeException -> getString(R.string.unknown_response_code_exception)
                is NotAuthException -> getString(R.string.not_auth_exception)
                is NoConnectionException -> getString(R.string.no_connection_exception)
                is NetworkException -> exception.message ?: getString(R.string.network_exception)
                else -> getString(R.string.unknown_exception)
            }
        }
    }
}