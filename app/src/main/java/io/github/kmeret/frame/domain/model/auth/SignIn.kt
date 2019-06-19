package io.github.kmeret.frame.domain.model.auth

data class SignIn (
    val basicAuthCredentials: BasicAuthCredentials,
    val oAuthCredentials: OAuthCredentials
)