package io.github.kmeret.frame.data.converters

import io.github.kmeret.frame.data.network.github.model.GithubProfile
import io.github.kmeret.frame.data.network.github.model.GithubRepo
import io.github.kmeret.frame.data.network.github.model.GithubUser
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.domain.model.User

object GithubConverter {
    fun fromNetwork(githubProfile: GithubProfile) = Profile(
        id = githubProfile.id,
        login = githubProfile.login,
        avatarUrl = githubProfile.avatarUrl,
        name = githubProfile.name,
        bio = githubProfile.bio,
        company = githubProfile.company,
        location = githubProfile.location
    )

    fun fromNetwork(githubRepo: GithubRepo) = Repo(
        id = githubRepo.id,
        name = githubRepo.name,
        fullName = githubRepo.fullName,
        description = githubRepo.description ?: "",
        language = githubRepo.language ?: "",
        starsCount = githubRepo.starsCount,
        forksCount = githubRepo.forksCount,
        topics = githubRepo.topics ?: listOf(),
        updatedAt = githubRepo.updatedAt
    )

    fun fromNetwork(githubUser: GithubUser) = User(
        id = githubUser.id,
        login = githubUser.login,
        avatarUrl = githubUser.avatarUrl
    )
}