package io.github.kmeret.frame.domain.cases

import io.github.kmeret.frame.application.App
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.domain.repos.UserRepo
import io.github.kmeret.frame.infrastructure.domain.coroutines.CoroutineUseCase

class UserInteractor(
    private val userRepo: UserRepo
) {

    private val username = App.GITHUB_USER

    suspend fun requestProfile(): Profile = userRepo.getProfile(username)

    fun requestRepoList() = object : CoroutineUseCase<List<Repo>>() {
        override suspend fun asyncRequest(): List<Repo> = userRepo.getRepoList(username)
    }

    fun requestStarredList() = object : CoroutineUseCase<List<Repo>>() {
        override suspend fun asyncRequest(): List<Repo> = userRepo.getStarredList(username)
    }

    fun requestFollowerList() = object : CoroutineUseCase<List<User>>() {
        override suspend fun asyncRequest(): List<User> = userRepo.getFollowerList(username)
    }

    fun requestFollowingList() = object : CoroutineUseCase<List<User>>() {
        override suspend fun asyncRequest(): List<User> = userRepo.getFollowingList(username)
    }
}