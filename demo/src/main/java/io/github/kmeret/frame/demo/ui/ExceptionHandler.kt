package io.github.kmeret.frame.demo.ui

import android.content.Context
import android.widget.Toast
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.network.*

object ExceptionHandler {

    fun handle(exception: Exception, context: Context) {
        val message = when (exception) {
            is ApiMessageException -> exception.message ?: context.getString(R.string.api_exception)
            is ApiException -> context.getString(R.string.api_exception)
            is UnknownResponseCodeException -> context.getString(R.string.unknown_response_code_exception)
            is NotAuthException -> context.getString(R.string.not_auth_exception)
            is NoConnectionException -> context.getString(R.string.no_connection_exception)
            is NetworkException -> exception.message ?: context.getString(R.string.network_exception)
            else -> exception.localizedMessage
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}