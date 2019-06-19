package io.github.kmeret.frame.domain.model.auth

data class SignOut(
    val token: String,
    val oAuthCredentials: OAuthCredentials
)