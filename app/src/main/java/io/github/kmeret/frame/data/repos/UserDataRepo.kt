package io.github.kmeret.frame.data.repos

import io.github.kmeret.frame.data.converters.GithubConverter
import io.github.kmeret.frame.data.network.user.GithubUserApi
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.domain.repos.UserRepo

class UserDataRepo(
    private val githubUserApi: GithubUserApi
) : UserRepo {
    override suspend fun getProfile(): Profile {
        return githubUserApi.getProfile()
            .let { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getRepoList(): List<Repo> {
        return githubUserApi.getRepoList()
            .map { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getStarredList(): List<Repo> {
        return githubUserApi.getStarredRepoList()
            .map { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getFollowerList(): List<User> {
        return githubUserApi.getFollowerList()
            .map { GithubConverter.fromNetwork(it) }
    }

    override suspend fun getFollowingList(): List<User> {
        return githubUserApi.getFollowingList()
            .map { GithubConverter.fromNetwork(it) }
    }
}