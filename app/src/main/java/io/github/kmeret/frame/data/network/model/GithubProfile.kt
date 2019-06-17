package io.github.kmeret.frame.data.network.model

import com.google.gson.annotations.SerializedName

data class GithubProfile(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String,
    val bio: String,
    val company: String,
    val location: String
)