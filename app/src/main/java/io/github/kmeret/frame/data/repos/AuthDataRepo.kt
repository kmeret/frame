package io.github.kmeret.frame.data.repos

import io.github.kmeret.frame.data.converters.AuthDataConverter
import io.github.kmeret.frame.data.network.login.GithubAuthApi
import io.github.kmeret.frame.data.storage.RealmAuthData
import io.github.kmeret.frame.domain.model.AuthCredentials
import io.github.kmeret.frame.domain.model.AuthData
import io.github.kmeret.frame.domain.repos.AuthRepo
import io.github.kmeret.frame.infrastructure.data.storage.RealmDao

class AuthDataRepo(
    private val githubAuthApi: GithubAuthApi,
    private val realmDao: RealmDao
) : AuthRepo {
    override suspend fun login(authCredentials: AuthCredentials): AuthData {
        return githubAuthApi.createAuth(AuthDataConverter.toNetwork(authCredentials))
            .let { AuthDataConverter.fromNetwork(it) }
    }

    override fun saveAuthData(authData: AuthData) {
        realmDao.save(AuthDataConverter.toStorage(authData))
    }

    override fun getToken(): String? {
        val realmAuthData = realmDao.findFirst<RealmAuthData>() ?: return null
        return realmAuthData
            .let { AuthDataConverter.fromStorage(it) }
            .token
    }
}