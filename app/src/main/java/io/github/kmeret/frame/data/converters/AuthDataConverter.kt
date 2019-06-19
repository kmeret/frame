package io.github.kmeret.frame.data.converters

import android.util.Base64
import io.github.kmeret.frame.data.network.login.GithubAuthRequest
import io.github.kmeret.frame.data.network.login.GithubAuthResponse
import io.github.kmeret.frame.data.storage.RealmAuthData
import io.github.kmeret.frame.domain.model.auth.AuthData
import io.github.kmeret.frame.domain.model.auth.BasicAuthCredentials
import io.github.kmeret.frame.domain.model.auth.OAuthCredentials
import io.github.kmeret.frame.infrastructure.domain.requiredNotNull
import java.nio.charset.StandardCharsets

object AuthDataConverter {
    fun toBasicAuth(basicAuthCredentials: BasicAuthCredentials): String {
        val bytes = "${basicAuthCredentials.username}:${basicAuthCredentials.password}".toByteArray(StandardCharsets.UTF_8)
        val base64 = Base64.encodeToString(bytes, Base64.DEFAULT).removeSuffix("\n")
        return "Basic $base64"
    }

    fun toGithubAuth(oAuthCredentials: OAuthCredentials) = GithubAuthRequest(
        clientId = oAuthCredentials.clientId,
        clientSecret = oAuthCredentials.clientSecret
    )

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