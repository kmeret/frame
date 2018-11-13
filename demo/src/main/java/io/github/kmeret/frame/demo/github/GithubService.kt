package io.github.kmeret.frame.demo.github

import io.github.kmeret.frame.demo.github.model.GithubRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{user}/starred")
    fun getStarredRepoListByUsername(@Path("user") user: String): Call<List<GithubRepo>>

}