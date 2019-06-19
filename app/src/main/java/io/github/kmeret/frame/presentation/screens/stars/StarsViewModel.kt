package io.github.kmeret.frame.presentation.screens.stars

import androidx.lifecycle.MutableLiveData
import io.github.kmeret.frame.domain.cases.UserInteractor
import io.github.kmeret.frame.domain.model.Repo
import io.github.kmeret.frame.infrastructure.application.lifecycle.BaseViewModel
import io.github.kmeret.frame.infrastructure.application.lifecycle.onNext

class StarsViewModel(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    val starredList = MutableLiveData<List<Repo>>()

    override fun onInit() {

    }

    override fun onBackPressed() {

    }

    fun requestStarredList() {
        userInteractor::requestStarredList.subscribe { starredList.onNext(it) }
    }

}
