package io.github.kmeret.frame.domain.repos

import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.domain.model.User

interface UserRepo {
    suspend fun getProfile(username: String): Profile
    suspend fun getRepoList(username: String): List<Repo>
    suspend fun getStarredList(username: String): List<Repo>
    suspend fun getFollowerList(username: String): List<User>
    suspend fun getFollowingList(username: String): List<User>
}