package io.github.kmeret.frame.data.network.model

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    val id: Long,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val description: String?,
    val language: String?,
    @SerializedName("stargazers_count")
    val starsCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    val topics: List<String>?,
    @SerializedName("updated_at")
    val updatedAt: String
)