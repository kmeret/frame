package io.github.kmeret.frame.demo.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.kmeret.frame.demo.domain.cases.NetworkLiveUseCase
import io.github.kmeret.frame.demo.github.GithubConfig
import io.github.kmeret.frame.demo.github.GithubService
import io.github.kmeret.frame.demo.github.model.GithubProfile
import io.github.kmeret.frame.demo.storage.dao.UserDao

class ProfileViewModel(private val githubService: GithubService, private val userDao: UserDao) : ViewModel() {

    private val requestProfileUseCase = NetworkLiveUseCase<GithubProfile>()

    val loading: LiveData<Boolean> = requestProfileUseCase.loading
    val empty: LiveData<Boolean> = requestProfileUseCase.empty
    val error: LiveData<Exception> = requestProfileUseCase.error

    init {
        Transformations.map(requestProfileUseCase.data) { githubUser ->
            userDao.update(githubUser.map())
        }
    }

    fun requestProfile() {
        requestProfileUseCase.execute(githubService.getProfile(GithubConfig.userName))
    }

//    fun getProfile(): LiveData<User> = Transformations.map(userDao.getById(1)) { roomUser ->
//
//    }

}