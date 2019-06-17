package io.github.kmeret.frame.data.converters

import android.util.Base64
import io.github.kmeret.frame.data.network.login.GithubAuthResponse
import io.github.kmeret.frame.data.storage.RealmAuthData
import io.github.kmeret.frame.domain.model.AuthCredentials
import io.github.kmeret.frame.domain.model.AuthData
import io.github.kmeret.frame.infrastructure.domain.requiredNotNull
import java.nio.charset.StandardCharsets

object AuthDataConverter {
    fun toNetwork(authCredentials: AuthCredentials): String {
        val bytes = "${authCredentials.login}:${authCredentials.password}".toByteArray(StandardCharsets.UTF_8)
        val base64 = Base64.encodeToString(bytes, Base64.DEFAULT)
        return "Basic $base64"
    }

    fun fromNetwork(githubAuthResponse: GithubAuthResponse) = AuthData(
        token = githubAuthResponse.token
    )

    fun toStorage(authData: AuthData) = RealmAuthData(
        token = authData.token
    )

    fun fromStorage(realmAuthData: RealmAuthData) = AuthData(
        token = realmAuthData.token.requiredNotNull()
    )
}