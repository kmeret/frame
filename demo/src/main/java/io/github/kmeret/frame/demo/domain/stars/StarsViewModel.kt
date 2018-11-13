package io.github.kmeret.frame.demo.domain.stars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//todo inject in constructor
class StarsViewModel(private val getStarredRepoUseCase: GetStarredRepoUseCase) : ViewModel() {


    private val repoList = MutableLiveData<List<Repo>>()
    fun getRepoList(): LiveData<List<Repo>> = repoList

    init {
        getStarredRepoUseCase.execute()
    }
}