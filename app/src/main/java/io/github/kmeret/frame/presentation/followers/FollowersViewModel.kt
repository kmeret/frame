package io.github.kmeret.frame.presentation.followers

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.domain.cases.UserInteractor
import io.github.kmeret.frame.domain.model.User
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext

class FollowersViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val followersList = MutableLiveData<List<User>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestUserList() {
        safeSubscribe {
            userInteractor.requestFollowersList().execute(
                isLoading = { isLoading.onNext(it) },
                onSuccess = { followersList.onNext(it) },
                onError = { errors.onNext(it) }
            )
        }
    }

}