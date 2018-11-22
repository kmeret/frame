package io.github.kmeret.frame.demo.ui.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.demo.github.GithubConfig
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.demo.github.model.GithubUser
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class FollowingViewModel(private val githubService: GithubService) : ViewModel() {

    private val disposableList = CompositeDisposable()
    private val data: LiveData<List<GithubUser>> = LiveDataReactiveStreams.fromPublisher(githubService.getFollowingList(GithubConfig.userName))
    //empty
    //loading
    //error

    init {
        requestFollowingList()
    }

    private fun requestFollowingList() {
        val disposable = githubService.getFollowingList(GithubConfig.userName).subscribeBy(
                onNext = { githubUserList -> },
                onError = {  },
                onComplete = {}
        )
        disposableList.addAll(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposableList.clear()
    }

}