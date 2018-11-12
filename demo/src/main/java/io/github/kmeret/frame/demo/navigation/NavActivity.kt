package io.github.kmeret.frame.demo.navigation

import android.os.Bundle
import io.github.kmeret.frame.android.base.BaseActivity
import io.github.kmeret.frame.demo.App
import io.github.kmeret.frame.demo.R
import io.github.kmeret.frame.demo.github.GithubRepo
import io.github.kmeret.frame.demo.github.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NavActivity : BaseActivity() {

    @Inject
    lateinit var githubService: GithubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        (application as App).appComponent.inject(this)

        githubService.getStarredRepoListByUsername("kmeret").enqueue(object : Callback<List<GithubRepo>> {
            override fun onResponse(call: Call<List<GithubRepo>>, response: Response<List<GithubRepo>>) {

            }

            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {

            }

        })

    }
}
