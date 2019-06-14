package io.github.kmeret.frame.domain.cases

import io.github.kmeret.frame.data.github.GithubConfig
import io.github.kmeret.frame.data.github.GithubOpenApi
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.infrastructure.domain.coroutines.CoroutineUseCase

class UserInteractor(
    private val githubOpenApi: GithubOpenApi
) {

    private val username = GithubConfig.userName

    fun requestProfile() = object : CoroutineUseCase<Profile>() {
        override suspend fun asyncRequest(): Profile = githubOpenApi.getProfile(username).map().map()
    }

}