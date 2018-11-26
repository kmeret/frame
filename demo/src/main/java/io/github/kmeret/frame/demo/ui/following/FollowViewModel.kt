package io.github.kmeret.frame.demo.ui.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.demo.domain.entity.User
import io.github.kmeret.frame.demo.github.GithubConfig
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.demo.github.model.GithubUser
import io.reactivex.disposables.CompositeDisposable

class FollowViewModel(private val githubService: GithubService) : ViewModel() {

    private val disposableList = CompositeDisposable()

    private val followingList: LiveData<List<GithubUser>> = LiveDataReactiveStreams.fromPublisher(
            githubService.getFollowingListRx(GithubConfig.userName)
    )
    private val followerList: LiveData<List<GithubUser>> = LiveDataReactiveStreams.fromPublisher(
            githubService.getFollowerListRx(GithubConfig.userName)
    )
    private val userList: MediatorLiveData<List<GithubUser>> = MediatorLiveData()



    //empty
    //loading
    //error

    init {
        userList.apply {
            addSource(followingList) {
                userList.value = userList.value?.toMutableList()?.apply { addAll(it) }
            }
            addSource(followerList) {
                userList.value = userList.value?.toMutableList()?.apply { addAll(it) }
            }
        }
    }

    fun getUserList(): LiveData<List<GithubUser>> = userList

    override fun onCleared() {
        super.onCleared()
        disposableList.clear()
    }

}