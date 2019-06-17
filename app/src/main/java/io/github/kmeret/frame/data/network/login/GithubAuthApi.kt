package io.github.kmeret.frame.data.network.login

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GithubAuthApi {

    @POST("authorizations")
    suspend fun createAuth(
        @Header("Authorization") basic: String,
        @Body githubAuthRequest: GithubAuthRequest = GithubAuthRequest()
    ): GithubAuthResponse

}