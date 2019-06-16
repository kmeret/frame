package io.github.kmeret.frame.domain.cases

import io.github.kmeret.frame.data.github.GithubConfig
import io.github.kmeret.frame.data.github.GithubOpenApi
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.infrastructure.domain.coroutines.CoroutineUseCase

class UserInteractor(
    private val githubOpenApi: GithubOpenApi
) {

    private val username = GithubConfig.userName

    fun requestProfile() = object : CoroutineUseCase<Profile>() {
        override suspend fun asyncRequest(): Profile = githubOpenApi.getProfile(username).map().map()
    }

    fun requestRepoList() = object : CoroutineUseCase<List<Repo>>() {
        override suspend fun asyncRequest(): List<Repo> = githubOpenApi.getRepoList(username).map { it.map() }
    }

    fun requestStarredList() = object : CoroutineUseCase<List<Repo>>() {
        override suspend fun asyncRequest(): List<Repo> = githubOpenApi.getStarredRepoList(username).map { it.map() }
    }

    fun requestFollowersList() = object : CoroutineUseCase<List<User>>() {
        override suspend fun asyncRequest(): List<User> = githubOpenApi.getFollowersList(username).map { it.map() }
    }

    fun requestFollowingList() = object : CoroutineUseCase<List<User>>() {
        override suspend fun asyncRequest(): List<User> = githubOpenApi.getFollowingList(username).map { it.map() }
    }
}