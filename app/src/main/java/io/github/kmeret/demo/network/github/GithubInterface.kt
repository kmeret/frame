package io.github.kmeret.demo.network.github

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubInterface {

    @GET("users/{user}/starred")
    fun getStarredRepoListByUsername(@Path("user") user: String): Flowable<List<GithubRepo>>

}