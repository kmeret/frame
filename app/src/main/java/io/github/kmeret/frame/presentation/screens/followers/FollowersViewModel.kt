package io.github.kmeret.frame.presentation.screens.followers

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.domain.cases.UserInteractor
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext

class FollowersViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val followerList = MutableLiveData<List<User>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestFollowerList() {
        userInteractor::requestFollowerList.subscribe { followerList.onNext(it) }
    }

}