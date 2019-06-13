package io.github.kmeret.frame.data.github

import io.github.kmeret.frame.data.github.model.GithubProfile
import io.github.kmeret.frame.data.github.model.GithubRepo
import io.github.kmeret.frame.data.github.model.GithubUser
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{userName}")
    suspend fun getProfile(@Path("userName") userName: String): GithubProfile

    @GET("users/{userName}/starred")
    fun getStarredRepoList(@Path("userName") userName: String): Call<List<GithubRepo>>

    @GET("users/{userName}/repos")
    fun getRepoList(@Path("userName") userName: String): Call<List<GithubRepo>>

    @GET("/users/{userName}/followers")
    fun getFollowerList(@Path("userName") userName: String): Call<List<GithubUser>>

    @GET("/users/{userName}/following")
    fun getFollowingList(@Path("userName") userName: String): Call<List<GithubUser>>

    @GET("/users/{userName}/followers")
    fun getFollowerListRx(@Path("userName") userName: String): Flowable<List<GithubUser>>

    @GET("/users/{userName}/following")
    fun getFollowingListRx(@Path("userName") userName: String): Flowable<List<GithubUser>>

}