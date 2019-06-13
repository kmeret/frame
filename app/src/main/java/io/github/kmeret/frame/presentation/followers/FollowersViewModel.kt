package io.github.kmeret.frame.presentation.followers

import androidx.lifecycle.*
import io.github.kmeret.frame.data.github.GithubConfig
import io.github.kmeret.frame.data.github.GithubService
import io.github.kmeret.frame.data.github.model.GithubUser
import io.github.kmeret.frame.domain.cases.NetworkLiveUseCase
import io.github.kmeret.frame.domain.entity.User
import io.github.kmeret.frame.infrastructure.application.lifecycle.DataEvent
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class FollowersViewModel(private val githubService: GithubService) : ViewModel() {

    private val disposableList = CompositeDisposable()

    private val requestFollowingUseCase = NetworkLiveUseCase<List<GithubUser>>()
    private val requestFollowersUseCase = NetworkLiveUseCase<List<GithubUser>>()

    private val userList: MutableLiveData<List<GithubUser>> = MutableLiveData()
    private val error: DataEvent<Throwable> =
        DataEvent()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()
    private val empty: DataEvent<Boolean> =
        DataEvent()

    fun requestUserListRx(lifecycleOwner: LifecycleOwner) {
        val userListFlowable = Flowable.merge(
                LiveDataReactiveStreams.toPublisher(lifecycleOwner, requestFollowingUseCase.data),
                LiveDataReactiveStreams.toPublisher(lifecycleOwner, requestFollowersUseCase.data)
        ).subscribeBy(
                onNext = {
                    empty.value = it.isEmpty()
                    loading.value = false
                    userList.value = it
                },
                onError = {
                    error.value = it
                }
        )
        disposableList.add(userListFlowable)

        requestFollowersUseCase.execute(githubService.getFollowingList(GithubConfig.userName))
        requestFollowingUseCase.execute(githubService.getFollowingList(GithubConfig.userName))
    }

    override fun onCleared() {
        super.onCleared()
        disposableList.clear()
    }

    fun getUserList(): LiveData<List<User>> = Transformations.map(userList) { githubUserList ->
        githubUserList.map { it.map() }
    }
    fun getError(): LiveData<Throwable> = error
    fun isLoading(): LiveData<Boolean> = loading
    fun isEmpty(): LiveData<Boolean> = empty

}