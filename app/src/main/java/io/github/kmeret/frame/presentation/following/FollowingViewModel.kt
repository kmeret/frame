package io.github.kmeret.frame.presentation.following

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

class FollowingViewModel(
    private val githubService: GithubService
) : BaseViewModel() {

    val followingList = MutableLiveData<List<User>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestFollowingList() {
        CoroutineScope(Dispatchers.IO).launch {
            val githubUserList = githubService.getFollowingList(GithubConfig.userName)
            withContext(Dispatchers.Main) {
                followingList.onNext(githubUserList.map { it.map() })
            }
        }
    }

}