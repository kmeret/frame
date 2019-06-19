package io.github.kmeret.frame.domain.cases

import io.github.kmeret.frame.BuildConfig
import io.github.kmeret.frame.domain.model.auth.*
import io.github.kmeret.frame.domain.repos.AuthRepo
import io.github.kmeret.frame.infrastructure.domain.coroutines.execute

class AuthInteractor(
    private val authRepo: AuthRepo
) {

    suspend fun signIn(basicAuthCredentials: BasicAuthCredentials): AuthData {
        return authRepo.signIn(SignIn(basicAuthCredentials, getOAuth()))
    }

    fun getAuthData() = authRepo.getAuthData()

    fun saveAuthData(authData: AuthData) =  authRepo.saveAuthData(authData)

    fun signOut() {
        val token = authRepo.getAuthToken()
        if (token == null) {
            authRepo.clearUserData()
            return
        }

        authRepo::signOut.execute(SignOut(token, getOAuth())) {
            authRepo.clearUserData()
        }
    }

    private fun getOAuth() = OAuthCredentials(
        BuildConfig.API_CLIENT_ID,
        BuildConfig.API_CLIENT_SECRET
    )
}