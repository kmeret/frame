package io.github.kmeret.frame.data.network.github.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)