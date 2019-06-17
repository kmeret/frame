package io.github.kmeret.frame.data.repos

import io.github.kmeret.frame.data.converters.GithubConverter
import io.github.kmeret.frame.data.network.github.GithubOpenApi
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.domain.repos.UserRepo

class UserDataRepo(
    private val githubOpenApi: GithubOpenApi
) : UserRepo {
    override suspend fun getProfile(username: String): Profile {
        return githubOpenApi.getProfile(username)
            .let { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getRepoList(username: String): List<Repo> {
        return githubOpenApi.getRepoList(username)
            .map { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getStarredList(username: String): List<Repo> {
        return githubOpenApi.getStarredRepoList(username)
            .map { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getFollowerList(username: String): List<User> {
        return githubOpenApi.getFollowerList(username)
            .map { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getFollowingList(username: String): List<User> {
        return githubOpenApi.getFollowingList(username)
            .map { GithubConverter.fromNetwork(it) }
    }
}