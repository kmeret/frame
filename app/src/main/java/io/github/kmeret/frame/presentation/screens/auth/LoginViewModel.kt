package io.github.kmeret.frame.presentation.screens.auth

import io.github.kmeret.frame.domain.cases.AuthInteractor
import io.github.kmeret.frame.domain.model.auth.AuthCommand
import io.github.kmeret.frame.domain.model.auth.BasicAuthCredentials
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel

class LoginViewModel(
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun onLoginButtonPressed(login: String, password: String) {
        authInteractor::signIn.subscribe(BasicAuthCredentials(login, password)) {
            authInteractor.saveAuthData(it)
            commands.onNext(AuthCommand.SignIn)
        }
    }
}