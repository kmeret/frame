package io.github.kmeret.frame.domain.cases

import io.github.kmeret.frame.domain.model.AuthCredentials
import io.github.kmeret.frame.domain.repos.AuthRepo

class AuthInteractor(
    private val authRepo: AuthRepo
) {

    fun signIn(authCredentials: AuthCredentials) {

    }
}