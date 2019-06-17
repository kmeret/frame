package io.github.kmeret.frame.data.repos

import io.github.kmeret.frame.data.converters.BasicAuthConverter
import io.github.kmeret.frame.data.network.login.GithubAuthApi
import io.github.kmeret.frame.domain.model.AuthCredentials
import io.github.kmeret.frame.domain.model.AuthData
import io.github.kmeret.frame.domain.repos.AuthRepo

class AuthDataRepo(
    private val githubAuthApi: GithubAuthApi
) : AuthRepo{
    override suspend fun login(authCredentials: AuthCredentials): AuthData {
        return githubAuthApi.createAuth(BasicAuthConverter.toRequest(authCredentials))
            .let { BasicAuthConverter.fromResponse(it) }
    }

    override fun saveAuthData(authData: AuthData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getToken(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}