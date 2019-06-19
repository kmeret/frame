package io.github.kmeret.frame.domain.cases

import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.repos.UserRepo

class UserInteractor(
    private val userRepo: UserRepo
) {

    suspend fun requestProfile(): Profile = userRepo.getProfile()

    suspend fun requestRepoList() = userRepo.getRepoList()

    suspend fun requestStarredList() = userRepo.getStarredList()

    suspend fun requestFollowerList() = userRepo.getFollowerList()

    suspend fun requestFollowingList() = userRepo.getFollowingList()
}