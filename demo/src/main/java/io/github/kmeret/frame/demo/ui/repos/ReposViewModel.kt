package io.github.kmeret.frame.demo.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.demo.domain.cases.NetworkLiveUseCase
import io.github.kmeret.frame.demo.domain.entity.Repo
import io.github.kmeret.frame.demo.github.GithubConfig
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.demo.github.model.GithubRepo
import io.github.kmeret.frame.lifecycle.DataEvent

class ReposViewModel(private val githubService: GithubService) : ViewModel() {

    private val useCase = NetworkLiveUseCase<List<GithubRepo>>()

    val repoList: LiveData<List<Repo>> = Transformations.map(useCase.data) { githubRepoList -> githubRepoList.map { it.map() } }
    val loading: LiveData<Boolean> = useCase.loading
    val empty: LiveData<Boolean> = useCase.empty
    val error: DataEvent<Exception> = useCase.error

    init {
        requestRepoList()
    }

    fun requestRepoList() = useCase.execute(githubService.getRepoList(GithubConfig.userName))

}