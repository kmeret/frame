package io.github.kmeret.frame.data.github

import io.github.kmeret.frame.BuildConfig
import io.github.kmeret.frame.infrastructure.data.network.ApiFactory
import io.github.kmeret.frame.infrastructure.data.network.HttpClientFactory
import io.github.kmeret.frame.infrastructure.data.network.ServerConfig

class GithubOpenApiFactory : ApiFactory(
    ServerConfig(BuildConfig.API_ENDPOINT),
    HttpClientFactory.okHttpClient()
)