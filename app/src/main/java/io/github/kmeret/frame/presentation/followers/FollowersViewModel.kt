package io.github.kmeret.frame.presentation.followers

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.data.github.GithubConfig
import io.github.kmeret.frame.data.github.GithubService
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FollowersViewModel(
    private val githubService: GithubService
) : BaseViewModel() {

    val followersList = MutableLiveData<List<User>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestUserList() {
        CoroutineScope(Dispatchers.IO).launch {
            val githubUserList = githubService.getFollowersList(GithubConfig.userName)
            withContext(Dispatchers.Main) {
                followersList.onNext(githubUserList.map { it.map() })
            }
        }
    }

}