package io.github.kmeret.frame.presentation.repos

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.data.github.GithubConfig
import io.github.kmeret.frame.data.github.GithubService
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(
    private val githubService: GithubService
) : BaseViewModel() {

    val repoList = MutableLiveData<List<Repo>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestRepoList()  {
        CoroutineScope(Dispatchers.IO).launch {
            val githubRepoList = githubService.getRepoList(GithubConfig.userName)
            withContext(Dispatchers.Main) {
                repoList.onNext(githubRepoList.map { it.map() })
            }
        }
    }

}