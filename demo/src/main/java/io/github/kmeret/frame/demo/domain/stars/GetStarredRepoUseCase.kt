package io.github.kmeret.frame.demo.domain.stars

import android.util.Log
import io.github.kmeret.frame.demo.github.GithubConfig
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.network.ApiException
import io.github.kmeret.frame.network.request

class GetStarredRepoUseCase(private val githubService: GithubService) {

    fun execute() {
        try {
            githubService.getStarredRepoListByUsername(GithubConfig.userName).request { repoList ->
                Log.d("test", repoList.joinToString { it.name } )
            }
        }
        catch (ex: ApiException) {

        }

    }

}
