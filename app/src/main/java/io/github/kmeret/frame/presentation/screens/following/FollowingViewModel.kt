package io.github.kmeret.frame.presentation.screens.following

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.domain.cases.UserInteractor
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext

class FollowingViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val followingList = MutableLiveData<List<User>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestFollowingList() {
        userInteractor::requestFollowingList.subscribe { followingList.onNext(it) }
    }

}