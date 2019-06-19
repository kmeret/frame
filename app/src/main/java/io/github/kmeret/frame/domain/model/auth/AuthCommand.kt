package io.github.kmeret.frame.domain.model.auth

import io.github.kmeret.frame.infrastructure.application.lifecycle.VMCommand


sealed class AuthCommand : VMCommand {
    object SignIn : AuthCommand()
    object SignOut : AuthCommand()
}
