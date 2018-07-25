package io.github.kmeret.demo.navigation

import android.os.Bundle
import android.util.Log
import io.github.kmeret.base.android.BaseActivity
import io.github.kmeret.demo.R
import io.github.kmeret.demo.network.github.GithubInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class NavActivity : BaseActivity() {

    private val githubInterface: GithubInterface by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        val sub = githubInterface.getStarredRepoListByUsername("kmeret")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            it.forEach {
                                Log.d("GithubRepo", it.toString())
                            }
                        },
                        { error ->
                            Log.d("GithubRepoError", error.message)
                        }
                )

    }
}
