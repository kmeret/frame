package io.github.kmeret.frame.presentation.followers

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
        safeSubscribe {
            userInteractor.requestFollowerList().execute(
                isLoading = { isLoading.onNext(it) },
                onSuccess = { followerList.onNext(it) },
                onError = { errors.onNext(it) }
            )
        }
    }

}