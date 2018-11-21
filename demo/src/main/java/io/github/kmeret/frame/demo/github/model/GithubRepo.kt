package io.github.kmeret.frame.demo.github.model

import com.google.gson.annotations.SerializedName
import io.github.kmeret.frame.demo.domain.entity.Repo

data class GithubRepo(
        val id: Long,
        val name: String,
        @SerializedName("full_name")
        val fullName: String,
        val description: String?,
        @SerializedName("language")
        val language: String?,
        @SerializedName("stargazers_count")
        val starsCount: Int,
        @SerializedName("forks_count")
        val forksCount: Int,
        val topics: List<String>?,
        @SerializedName("updated_at")
        val updatedAt: String
) {
    fun map() = Repo(
            id, name, fullName, description ?: "",
            language ?: "", starsCount, forksCount,
            topics ?: listOf(), updatedAt
    )
}