package io.github.kmeret.frame.data.network.login

import retrofit2.http.*

interface GithubAuthApi {

    @POST("authorizations")
    suspend fun createAuth(
        @Header("Authorization") basicAuth: String,
        @Body githubAuthRequest: GithubAuthRequest
    ): GithubAuthResponse

    @DELETE("applications/{client_id}/tokens/{access_token}")
    suspend fun revokeAuth(
        @Header("Authorization") basicAuth: String,
        @Path("client_id") clientId: String,
        @Path("access_token") token: String
    )

}