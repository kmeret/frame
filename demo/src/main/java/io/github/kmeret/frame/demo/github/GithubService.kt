package io.github.kmeret.frame.demo.github

import io.github.kmeret.frame.demo.github.model.GithubRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{userName}/starred")
    fun getStarredRepoList(@Path("userName") userName: String): Call<List<GithubRepo>>

    @GET("users/{userName}/repos")
    fun getRepoList(@Path("userName") userName: String): Call<List<GithubRepo>>

}