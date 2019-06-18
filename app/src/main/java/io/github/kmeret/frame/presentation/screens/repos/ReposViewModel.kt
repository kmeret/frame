package io.github.kmeret.frame.presentation.screens.repos

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.domain.cases.UserInteractor
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext

class ReposViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val repoList = MutableLiveData<List<Repo>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestRepoList() {
        safeSubscribe {
            userInteractor.requestRepoList().execute(
                isLoading = { isLoading.onNext(it) },
                onSuccess = { repoList.onNext(it) },
                onError = { errors.onNext(it) }
            )
        }
    }

}