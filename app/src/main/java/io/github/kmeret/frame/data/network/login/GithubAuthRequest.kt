package io.github.kmeret.frame.data.network.login

import com.google.gson.annotations.SerializedName

data class GithubAuthRequest(
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String,
    val note: String = "note"
)