package io.github.kmeret.frame.domain.repos

import io.github.kmeret.frame.domain.model.auth.AuthData
import io.github.kmeret.frame.domain.model.auth.SignIn
import io.github.kmeret.frame.domain.model.auth.SignOut

interface AuthRepo {
    suspend fun signIn(signIn: SignIn): AuthData
    suspend fun signOut(signOut: SignOut)
    fun saveAuthData(authData: AuthData)
    fun getAuthData(): AuthData?
    fun getAuthToken(): String?
    fun clearUserData()
}