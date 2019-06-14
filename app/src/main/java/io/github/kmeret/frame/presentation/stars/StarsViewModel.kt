package io.github.kmeret.frame.presentation.stars

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

class StarsViewModel(
    private val githubService: GithubService
) : BaseViewModel() {

    val starredList = MutableLiveData<List<Repo>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestStarredList() {
        CoroutineScope(Dispatchers.IO).launch {
            val githubRepoList = githubService.getStarredRepoList(GithubConfig.userName)
            withContext(Dispatchers.Main) {
                starredList.onNext(githubRepoList.map { it.map() })
            }
        }
    }

}
