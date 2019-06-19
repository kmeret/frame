package io.github.kmeret.frame.data.repos

import io.github.kmeret.frame.data.converters.AuthDataConverter
import io.github.kmeret.frame.data.network.login.GithubAuthApi
import io.github.kmeret.frame.data.storage.RealmAuthData
import io.github.kmeret.frame.domain.model.auth.AuthData
import io.github.kmeret.frame.domain.model.auth.BasicAuthCredentials
import io.github.kmeret.frame.domain.model.auth.SignIn
import io.github.kmeret.frame.domain.model.auth.SignOut
import io.github.kmeret.frame.domain.repos.AuthRepo
import io.github.kmeret.frame.infrastructure.data.storage.RealmDao

class AuthDataRepo(
    private val githubAuthApi: GithubAuthApi,
    private val realmDao: RealmDao
) : AuthRepo {
    override suspend fun signIn(signIn: SignIn): AuthData {
        return githubAuthApi.createAuth(
            basicAuth = AuthDataConverter.toBasicAuth(signIn.basicAuthCredentials),
            githubAuthRequest = AuthDataConverter.toGithubAuth(signIn.oAuthCredentials)
        ).let { AuthDataConverter.fromNetwork(it) }
    }

    override suspend fun signOut(signOut: SignOut) {
        return githubAuthApi.revokeAuth(
            basicAuth = AuthDataConverter.toBasicAuth(
                BasicAuthCredentials(
                    username = signOut.oAuthCredentials.clientId,
                    password = signOut.oAuthCredentials.clientSecret
                )
            ),
            token = signOut.token,
            clientId = signOut.oAuthCredentials.clientId
        )
    }

    override fun saveAuthData(authData: AuthData) {
        realmDao.save(AuthDataConverter.toStorage(authData))
    }

    override fun getAuthData(): AuthData? {
        val realmAuthData = realmDao.findFirst<RealmAuthData>() ?: return null
        return realmAuthData
            .let { AuthDataConverter.fromStorage(it) }
    }

    override fun getAuthToken(): String? {
        return getAuthData()?.token
    }

    override fun clearUserData() {
        realmDao.deleteAll()
    }
}