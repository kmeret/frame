package io.github.kmeret.frame.domain.repos

import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.domain.model.User

interface UserRepo {
    suspend fun getProfile(): Profile
    suspend fun getRepoList(): List<Repo>
    suspend fun getStarredList(): List<Repo>
    suspend fun getFollowerList(): List<User>
    suspend fun getFollowingList(): List<User>
}