package io.github.kmeret.frame.data.network.open

import io.github.kmeret.frame.data.network.model.GithubProfile
import io.github.kmeret.frame.data.network.model.GithubRepo
import io.github.kmeret.frame.data.network.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubOpenApi {

    @GET("users/{username}")
    suspend fun getProfile(@Path("username") username: String): GithubProfile

    @GET("users/{username}/starred")
    suspend fun getStarredRepoList(@Path("username") username: String): List<GithubRepo>

    @GET("users/{username}/repos")
    suspend fun getRepoList(@Path("username") username: String): List<GithubRepo>

    @GET("/users/{username}/followers")
    suspend fun getFollowerList(@Path("username") username: String): List<GithubUser>

    @GET("/users/{username}/following")
    suspend fun getFollowingList(@Path("username") username: String): List<GithubUser>

}