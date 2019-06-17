package io.github.kmeret.frame.data.network.github.model

import com.google.gson.annotations.SerializedName
import io.github.kmeret.frame.data.storage.entity.RoomProfile

data class GithubProfile(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String,
    val bio: String,
    val company: String,
    val location: String
) {
    fun map() = RoomProfile(id, login, avatarUrl, name, bio, company, location)
}