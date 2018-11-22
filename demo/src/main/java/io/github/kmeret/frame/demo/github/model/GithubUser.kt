package io.github.kmeret.frame.demo.github.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
        val id: Long,
        val login: String,
        val name: String,
        val company: String,
        val location: String,
        val bio: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
)