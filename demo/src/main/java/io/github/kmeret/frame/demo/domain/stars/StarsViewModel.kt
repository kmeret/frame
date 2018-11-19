package io.github.kmeret.frame.demo.domain.stars

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.demo.domain.cases.NetworkLiveUseCase
import io.github.kmeret.frame.demo.domain.entity.Repo
import io.github.kmeret.frame.demo.github.GithubConfig
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.demo.github.model.GithubRepo

class StarsViewModel(githubService: GithubService) : ViewModel() {

    private val useCase = NetworkLiveUseCase<List<GithubRepo>>()

    private val repoList = Transformations.map(useCase.data) { githubRepoList ->
        githubRepoList.map { it.map() }
    }

    fun getRepoList(): LiveData<List<Repo>> = repoList

    init {
        useCase.execute(githubService.getStarredRepoListByUsername(GithubConfig.userName))
    }

}
