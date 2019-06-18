package io.github.kmeret.frame.domain.model

import io.github.kmeret.frame.infrastructure.application.lifecycle.VMCommand


sealed class AuthCommand : VMCommand {
    object Login : AuthCommand()
    object Logout : AuthCommand()
}
