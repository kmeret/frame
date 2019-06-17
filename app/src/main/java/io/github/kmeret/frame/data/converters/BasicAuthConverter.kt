package io.github.kmeret.frame.data.converters

import android.util.Base64
import io.github.kmeret.frame.data.network.login.GithubAuthResponse
import io.github.kmeret.frame.domain.model.AuthCredentials
import io.github.kmeret.frame.domain.model.AuthData
import java.nio.charset.StandardCharsets

object BasicAuthConverter {
    fun toRequest(authCredentials: AuthCredentials): String {
        val bytes = "${authCredentials.login}:${authCredentials.password}".toByteArray(StandardCharsets.UTF_8)
        val base64 = Base64.encodeToString(bytes, Base64.DEFAULT)
        return "Basic $base64"
    }

    fun fromResponse(githubAuthResponse: GithubAuthResponse) = AuthData(
        token = githubAuthResponse.token
    )
}