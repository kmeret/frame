package io.github.kmeret.frame.presentation.profile

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.domain.cases.UserInteractor
import io.github.kmeret.frame.domain.model.Profile
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext

class ProfileViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val networkProfile = MutableLiveData<Profile>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestProfile() {
        safeSubscribe {
            userInteractor.requestProfile().execute(
                isLoading = { isLoading.onNext(it) },
                onSuccess = { networkProfile.onNext(it) },
                onError = { errors.onNext(it) }
            )
        }
    }
}