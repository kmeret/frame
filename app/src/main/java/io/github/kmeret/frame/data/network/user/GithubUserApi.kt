package io.github.kmeret.frame.data.network.user

import io.github.kmeret.frame.data.network.model.GithubProfile
import io.github.kmeret.frame.data.network.model.GithubRepo
import io.github.kmeret.frame.data.network.model.GithubUser
import retrofit2.http.GET

interface GithubUserApi {

    @GET("user")
    suspend fun getProfile(): GithubProfile

    @GET("user/starred")
    suspend fun getStarredRepoList(): List<GithubRepo>

    @GET("user/repos")
    suspend fun getRepoList(): List<GithubRepo>

    @GET("user/followers")
    suspend fun getFollowerList(): List<GithubUser>

    @GET("user/following")
    suspend fun getFollowingList(): List<GithubUser>
}