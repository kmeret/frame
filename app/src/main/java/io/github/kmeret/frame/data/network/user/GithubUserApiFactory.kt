package io.github.kmeret.frame.data.network.user

import io.github.kmeret.frame.BuildConfig
import io.github.kmeret.frame.domain.repos.AuthRepo
import io.github.kmeret.frame.infrastructure.data.network.ApiFactory
import io.github.kmeret.frame.infrastructure.data.network.HttpClientFactory
import io.github.kmeret.frame.infrastructure.data.network.ServerConfig

class GithubUserApiFactory (
    private val authRepo: AuthRepo
): ApiFactory(
    ServerConfig(BuildConfig.API_ENDPOINT),
    HttpClientFactory.okHttpClient {
        addInterceptor(AuthInterceptor(authRepo))
    }
)