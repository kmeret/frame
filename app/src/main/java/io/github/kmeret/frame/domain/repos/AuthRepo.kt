package io.github.kmeret.frame.domain.repos

import io.github.kmeret.frame.domain.model.AuthCredentials
import io.github.kmeret.frame.domain.model.AuthData

interface AuthRepo {
    suspend fun login(authCredentials: AuthCredentials): AuthData
    fun saveAuthData(authData: AuthData)
    fun getToken(): String?
}